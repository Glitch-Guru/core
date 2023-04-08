package pl.glitchguru.issuetracker.controller.request;

public record RegisterUserAndAccountRequest(String firstName, String lastName, String accountName, String email, String password) {

    public boolean isValid() {
        return firstName != null && lastName != null && accountName != null && email != null && password != null;
    }

}
