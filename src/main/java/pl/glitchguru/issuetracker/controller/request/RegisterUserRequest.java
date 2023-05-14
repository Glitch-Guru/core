package pl.glitchguru.issuetracker.controller.request;

public record RegisterUserRequest(String firstName, String lastName, String email, String password) {

    public boolean isValid() {
        return firstName != null && lastName != null && email != null && password != null;
    }

}
