package pl.glitchguru.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.glitchguru.issuetracker.controller.request.RegisterUserRequest;
import pl.glitchguru.issuetracker.controller.request.AuthenticationRequest;
import pl.glitchguru.issuetracker.controller.response.AuthenticationResponse;
import pl.glitchguru.issuetracker.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterUserRequest request
    ) {
        if (!request.isValid()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.registerAndCreateNewUser(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
            ) {
        log.info("Trying to authenticate user: {}", request.email());
        if (!request.isValid()) {
            log.info("Invalid authentication request: {}", request);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.authenticate(request));
    }


}
