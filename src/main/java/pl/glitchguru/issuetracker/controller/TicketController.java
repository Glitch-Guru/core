package pl.glitchguru.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.glitchguru.issuetracker.controller.request.TicketCreationRequest;
import pl.glitchguru.issuetracker.controller.request.TicketUpdateRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @RequestBody TicketUpdateRequest request) {
        final boolean isTicketFound = ticketService.updateTicket(id, request);
        if (isTicketFound) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        final boolean isTicketFound = ticketService.deleteTicket(id);
        if (isTicketFound) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
