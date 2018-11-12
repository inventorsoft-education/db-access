package co.inventorsoft.academy.emails.sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.inventorsoft.academy.emails.entity.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, Integer> {
	
	List<Email> findAll();
	Optional<Email> findById(Integer id);
	<S extends Email> S save(Email email);
	<S extends Email> Iterable<S> saveAll(Iterable<S> emails);
	
	@Modifying
	@Query("update Email e set e.sentDate = :date where e.id = :id")
	int setEmailSentDateById(@Param("id") int id, @Param("date") LocalDateTime date);
	
	void deleteById(Integer id);
	void deleteAll();

}
