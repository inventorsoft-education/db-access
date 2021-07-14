package co.inventrosoft.hibernate_task.repository;

import co.inventrosoft.hibernate_task.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    boolean existsByName(String name);
}
