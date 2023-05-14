package pl.glitchguru.issuetracker.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.glitchguru.issuetracker.controller.request.RegisterUserRequest;
import pl.glitchguru.issuetracker.controller.request.AuthenticationRequest;
import pl.glitchguru.issuetracker.controller.response.AuthenticationResponse;
import pl.glitchguru.issuetracker.model.authentication.Token;
import pl.glitchguru.issuetracker.model.core.User;
import pl.glitchguru.issuetracker.repository.TokenRepository;
import pl.glitchguru.issuetracker.repository.UserRepository;
import pl.glitchguru.issuetracker.util.JwtService;

import static pl.glitchguru.issuetracker.model.authentication.Role.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerAndCreateNewUser(RegisterUserRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException("User with given email already exists");
        }

        final var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(USER)
                .build();

        final var savedUser = userRepository.save(user);
        final var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);
        log.info("Created new user: {}", savedUser);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        final var user = userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        final var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        final var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        final var validUserTokens = tokenRepository.findAllByUserAndExpiredIsFalseOrRevokedIsFalse(user);

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }
}