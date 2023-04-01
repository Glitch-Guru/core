package uj.student.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uj.student.issuetracker.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
