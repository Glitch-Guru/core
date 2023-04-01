package uj.student.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uj.student.issuetracker.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
