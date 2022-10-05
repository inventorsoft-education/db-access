package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.repository.TeamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamService {
    TeamRepository teamRepository;

    @Transactional
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    @Transactional
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    public int getSize() {
        return (int)teamRepository.count();
    }
}
