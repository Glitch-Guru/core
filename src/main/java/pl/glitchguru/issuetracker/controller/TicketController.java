package pl.glitchguru.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.glitchguru.issuetracker.controller.request.TicketCreationRequest;
import pl.glitchguru.issuetracker.controller.response.TicketResponse;
import pl.glitchguru.issuetracker.model.core.User;
import pl.glitchguru.issuetracker.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @RequestBody TicketCreationRequest request,
            Authentication authentication
    ) {
        final var user = (User) authentication.getPrincipal();

        if (!request.isValid()) {
            return ResponseEntity.badRequest().build();
        }

        log.info("User {} is creating ticket {}", user, request);

        return ResponseEntity.ok(ticketService.createTicket(request, user));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }



}
