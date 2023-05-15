package pl.glitchguru.issuetracker.controller.request;

public record TicketCreationRequest(String title, String description, String status, Long assigneeId) {

        public boolean isValid() {
            return title != null && description != null;
        }
}
