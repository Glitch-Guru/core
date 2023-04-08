package pl.glitchguru.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.glitchguru.issuetracker.model.core.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
