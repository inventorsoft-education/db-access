package co.inventorsoft.academy.service;


import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.repository.TeamRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamService {
    TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public int getSize() {
        return teamRepository.findAll().size();
    }
}
