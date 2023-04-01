package uj.student.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uj.student.issuetracker.model.Account;
import uj.student.issuetracker.repository.AccountRepository;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/accounts/")
@RequiredArgsConstructor
public class AccountController {

    final AccountRepository accountRepository;

    // This endpoint is for demonstration purposes only, it is not used in the application.
    // We should not allow to create accounts without users.
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (account == null || !account.isValid()) {
            return ResponseEntity.badRequest().build();
        }

        final var savedAccount = accountRepository.save(account);
        return new ResponseEntity<>(savedAccount, CREATED);
    }
}
