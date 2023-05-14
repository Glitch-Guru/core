package pl.glitchguru.issuetracker.model.authentication;

import jakarta.persistence.*;
import lombok.*;
import pl.glitchguru.issuetracker.model.core.User;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @ToString.Exclude
    private String token;

    private boolean revoked;

    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}