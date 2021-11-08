package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Team;
import com.inventorsoft.domain.repository.TeamRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService extends GeneralService<Team, Integer>{

    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        super(teamRepository);
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Transactional
    public Team createTestTeam() {
        Team team = new Team();

        team.setName("TestNameTeam");
        team.setCaptain("TestCaptainNAme");
        team.setCoach("TestCoachName");

        return teamRepository.save(team);
    }

    @Transactional
    public List<Team> setAll(List<Team> teams) {
        register(teams);
       return teamRepository.saveAll(teams);
    }

    private boolean isPowerOfTwo(int x) {
        return x != 0 && ((x & (x - 1)) == 0);
    }

    private List<Team> register(List<Team> teams) {
        while (!isPowerOfTwo(teams.size())) {
            if (teams.size() <= 3) {
                throw new IllegalArgumentException("Number of commands must be >= 4");
            }
            teams.remove(teams.size() - 1);
        }
        return teams;
    }
}
