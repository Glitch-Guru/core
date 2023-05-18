package pl.glitchguru.issuetracker.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import pl.glitchguru.issuetracker.controller.request.TicketCreationRequest;
import pl.glitchguru.issuetracker.controller.request.TicketUpdateRequest;
import pl.glitchguru.issuetracker.controller.response.TicketResponse;
import pl.glitchguru.issuetracker.model.core.User;
import pl.glitchguru.issuetracker.model.tickets.Ticket;
import pl.glitchguru.issuetracker.repository.TicketRepository;
import pl.glitchguru.issuetracker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private static final String DEFAULT_TICKET_CREATION_STATUS = "Open";

    public TicketResponse createTicket(TicketCreationRequest ticketCreationRequest, User reporter) {
        User assignee = reporter;

        if (ticketCreationRequest.assigneeId() != null) {
            assignee = userRepository.findById(ticketCreationRequest.assigneeId())
                                     .orElseThrow(() -> new RuntimeException("User not found"));
        }

        final Ticket ticket = Ticket.builder()
                                    .title(ticketCreationRequest.title())
                                    .description(ticketCreationRequest.description())
                                    .status(ticketCreationRequest.status() == null ? DEFAULT_TICKET_CREATION_STATUS : ticketCreationRequest.status())
                                    .assignee(assignee)
                                    .reporter(reporter)
                                    .build();

        ticketRepository.save(ticket);

        return TicketResponse.from(ticket);
    }

    public List<TicketResponse> getAllTickets() {
        return Lists.newArrayList(ticketRepository
                                  .findAll()
                                  .iterator())
                    .stream()
                    .map(TicketResponse::from)
                    .toList();
    }

    public Optional<TicketResponse> getTicket(Long id) {
        return ticketRepository.findById(id).map(TicketResponse::from);
    }

    public boolean updateTicket(Long id, TicketUpdateRequest ticketUpdateRequest) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isEmpty()) {
            return false;
        }

        Ticket ticket = optionalTicket.get();
        User assignee = getAssignee(ticketUpdateRequest);

        Ticket updatedTicket = Ticket.builder()
                .id(id)
                .title(ticketUpdateRequest.title() == null ? ticket.getTitle() : ticketUpdateRequest.title())
                .description(ticketUpdateRequest.description() == null ? ticket.getDescription() : ticketUpdateRequest.description())
                .status(ticketUpdateRequest.status() == null ? ticket.getStatus() : ticketUpdateRequest.status())
                .assignee(assignee)
                .reporter(ticket.getReporter())
                .build();

        ticketRepository.save(updatedTicket);

        return true;
    }

    @Nullable
    private User getAssignee(TicketUpdateRequest ticketUpdateRequest) {
        User assignee = null;
        if (ticketUpdateRequest.assigneeId() != null) {
            assignee = userRepository.findById(ticketUpdateRequest.assigneeId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }
        return assignee;
    }

    public boolean deleteTicket(Long id) {
        if (ticketRepository.findById(id).isEmpty()) {
            return false;
        }
        ticketRepository.deleteById(id);
        return true;
    }

}
