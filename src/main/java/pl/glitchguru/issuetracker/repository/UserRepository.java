package pl.glitchguru.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.glitchguru.issuetracker.model.core.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailIgnoreCaseAndAccount_Id(String email, Long accountId);

    Optional<User> findByEmailIgnoreCase(String email);

}
