package pl.glitchguru.issuetracker.controller.request;

public record TicketUpdateRequest(String title, String description, String status, Long assigneeId) {

}
