package pl.glitchguru.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.glitchguru.issuetracker.model.authentication.Token;
import pl.glitchguru.issuetracker.model.core.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    List<Token> findAllByUserAndExpiredIsFalseOrRevokedIsFalse(User user);


}
