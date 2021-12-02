package com.example.demo.service;

import com.example.demo.entity.Team;
import com.example.demo.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DBService {
    TeamRepository teamRepository;
    Team team;

    public void addTeam(String teamName, String coachName, String captainName) {
        Team team = new Team(teamName, coachName, captainName);
        teamRepository.save(team);
    }

    public void setTeams(List list) {
        teamRepository.saveAll(list);
    }

    public void winnerPoints(Team team) {
        team.setPoints(team.getPoints() + 3);
        teamRepository.save(team);
    }

    public void drawPoints(Team firstTeam, Team secondTeam) {
        firstTeam.setPoints(team.getPoints() + 1);
        secondTeam.setPoints(team.getPoints() + 1);
        teamRepository.save(firstTeam);
        teamRepository.save(secondTeam);
    }

    public void cleanTale() {
        teamRepository.deleteAll();
    }
}

