package co.inventorsoft.academy.springBootTask.repository;

import co.inventorsoft.academy.springBootTask.domain.entity.Team;

import java.util.List;

public interface TeamRepository {

    List<Team> findAll();

    Team save(Team team);

}
