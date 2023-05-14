package pl.glitchguru.issuetracker.controller.response;

import pl.glitchguru.issuetracker.model.tickets.Ticket;

public record TicketResponse(Long id, String title, String description, String status, Long assigneeId,
                             Long reporterId) {
    public static TicketResponse from(Ticket ticket) {
        return new TicketResponse(ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus(),
                ticket.getAssignee().getId(), ticket.getReporter().getId());
    }

}
