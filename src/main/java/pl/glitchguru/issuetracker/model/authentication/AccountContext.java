package pl.glitchguru.issuetracker.model.authentication;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountContext {

    private static final ThreadLocal<Long> CURRENT_ACCOUNT_ID = new ThreadLocal<>();

    public static Long getCurrentAccountId() {
        return CURRENT_ACCOUNT_ID.get();
    }

    public static void setCurrentAccountId(Long id) {
        CURRENT_ACCOUNT_ID.set(id);
    }
}