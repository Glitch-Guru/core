package pl.glitchguru.issuetracker.model.tickets;

import jakarta.persistence.*;
import lombok.*;
import pl.glitchguru.issuetracker.model.core.User;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

}
