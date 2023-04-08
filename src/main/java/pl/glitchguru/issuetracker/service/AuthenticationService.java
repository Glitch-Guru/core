package pl.glitchguru.issuetracker.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.glitchguru.issuetracker.controller.request.RegisterUserAndAccountRequest;
import pl.glitchguru.issuetracker.model.authentication.AuthenticationRequest;
import pl.glitchguru.issuetracker.model.authentication.AuthenticationResponse;
import pl.glitchguru.issuetracker.model.authentication.Token;
import pl.glitchguru.issuetracker.model.core.Account;
import pl.glitchguru.issuetracker.model.core.User;
import pl.glitchguru.issuetracker.repository.AccountRepository;
import pl.glitchguru.issuetracker.repository.TokenRepository;
import pl.glitchguru.issuetracker.repository.UserRepository;
import pl.glitchguru.issuetracker.util.JwtService;

import static pl.glitchguru.issuetracker.model.authentication.Role.*;
import static pl.glitchguru.issuetracker.model.authentication.AccountContext.getCurrentAccountId;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerAndCreateNewAccount(RegisterUserAndAccountRequest request) {
        final var account = Account.builder()
                .name(request.accountName())
                .build();

        final var savedAccount = accountRepository.save(account);

        final var user = User.builder()
                .account(savedAccount)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(ADMIN)
                .build();

        final var savedUser = userRepository.save(user);
        final var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final var user = userRepository.findByEmailIgnoreCaseAndAccount_Id(request.email(), getCurrentAccountId())
                .orElseThrow();

        final var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
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