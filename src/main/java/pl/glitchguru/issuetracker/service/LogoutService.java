package pl.glitchguru.issuetracker.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import pl.glitchguru.issuetracker.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    private static final String BEARER = "Bearer ";

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            return;
        }

        final String jwt = authHeader.substring(BEARER.length() - 1); // subtract one because index starts from 0
        tokenRepository.findByToken(jwt).ifPresent(token -> {
            token.setExpired(true);
            token.setRevoked(true);

            tokenRepository.save(token);
            SecurityContextHolder.clearContext();
        });

    }
}