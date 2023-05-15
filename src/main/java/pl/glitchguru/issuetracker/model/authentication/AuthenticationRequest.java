package pl.glitchguru.issuetracker.model.authentication;

public record AuthenticationRequest(String email, String password) {

    public boolean isValid() {
        return email != null && password != null;
    }

}
