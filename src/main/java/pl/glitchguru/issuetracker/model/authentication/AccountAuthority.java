package pl.glitchguru.issuetracker.model.authentication;

import org.springframework.security.core.GrantedAuthority;
import pl.glitchguru.issuetracker.model.core.Account;

public class AccountAuthority implements GrantedAuthority {

    private final Account account;

    public AccountAuthority(Account account) {
        this.account = account;
    }

    @Override
    public String getAuthority() {
        return account.getId().toString();
    }
}
