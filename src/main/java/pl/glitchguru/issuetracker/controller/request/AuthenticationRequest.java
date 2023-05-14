package pl.glitchguru.issuetracker.controller.request;

public record AuthenticationRequest(String email, String password) {

    public boolean isValid() {
        return email != null && password != null;
    }

}
