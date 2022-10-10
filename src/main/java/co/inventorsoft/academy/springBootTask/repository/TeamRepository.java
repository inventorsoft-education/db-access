package co.inventorsoft.academy.springBootTask.repository;

import co.inventorsoft.academy.springBootTask.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
