package co.inventrosoft.hibernate_task.repository;

import co.inventrosoft.hibernate_task.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

}
