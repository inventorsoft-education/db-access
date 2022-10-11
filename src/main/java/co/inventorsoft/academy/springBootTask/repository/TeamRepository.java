package co.inventorsoft.academy.springBootTask.repository;

import co.inventorsoft.academy.springBootTask.domain.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository {

    List<Team> findAll();

    Team save(Team team);

}
