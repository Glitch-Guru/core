package pl.glitchguru.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.glitchguru.issuetracker.controller.request.RegisterUserAndAccountRequest;
import pl.glitchguru.issuetracker.model.authentication.AuthenticationRequest;
import pl.glitchguru.issuetracker.model.authentication.AuthenticationResponse;
import pl.glitchguru.issuetracker.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterUserAndAccountRequest request
    ) {
        if (!request.isValid()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.registerAndCreateNewAccount(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
            ) {
        if (!request.isValid()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.authenticate(request));
    }


}
