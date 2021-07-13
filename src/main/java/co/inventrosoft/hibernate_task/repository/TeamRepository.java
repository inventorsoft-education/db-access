package co.inventrosoft.hibernate_task.repository;

import co.inventrosoft.hibernate_task.model.Team;

import java.util.List;

public interface TeamRepository {
    void save(List<Team> teams);
    void add(Team team);
    void update(Team team);
    boolean isExist(String teamName);
}
