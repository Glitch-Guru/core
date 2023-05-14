package pl.glitchguru.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.glitchguru.issuetracker.model.tickets.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
