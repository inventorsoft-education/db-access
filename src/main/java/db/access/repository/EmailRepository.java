package db.access.repository;

import db.access.model.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<Email,Long> {
}
