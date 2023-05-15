package pl.glitchguru.issuetracker.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.glitchguru.issuetracker.controller.request.TicketCreationRequest;
import pl.glitchguru.issuetracker.controller.response.TicketResponse;
import pl.glitchguru.issuetracker.model.core.User;
import pl.glitchguru.issuetracker.model.tickets.Ticket;
import pl.glitchguru.issuetracker.repository.TicketRepository;
import pl.glitchguru.issuetracker.repository.UserRepository;

import java.util.List;

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


}
