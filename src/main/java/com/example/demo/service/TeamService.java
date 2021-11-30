package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamService {

    TeamRepository teamRepository;

    public List<Team> teamsToList() {
        return teamRepository.findAll();
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> saveTeams(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    public void removeTeam(Team team) {
        teamRepository.delete(team);
    }

    public Team getTeamById(int id) {
        return teamRepository.getById(id);
    }


    public List<Team> createList() {
        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team("team1", "cap1", "coach1"));
        teamList.add(new Team("team2", "cap2", "coach2"));
        teamList.add(new Team("team3", "cap3", "coach3"));
        teamList.add(new Team("team4", "cap4", "coach4"));
        teamList.add(new Team("team5", "cap5", "coach5"));
        teamList.add(new Team("team6", "cap6", "coach6"));
        teamList.add(new Team("team7", "cap7", "coach7"));
        teamList.add(new Team("team8", "cap8", "coach8"));
        return teamList;
    }

}
