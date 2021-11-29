package com.example.jdbcdemo.repository;

import com.example.jdbcdemo.configuration.JdbcConfig;
import com.example.jdbcdemo.model.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamList {

    List<Team> teamList;
    JdbcConfig jdbcConfig;

    void createList() {
        teamList.add(new Team("team1", "cap1", "coach1"));
        teamList.add(new Team("team2", "cap2", "coach2"));
        teamList.add(new Team("team3", "cap3", "coach3"));
        teamList.add(new Team("team4", "cap4", "coach4"));
        teamList.add(new Team("team5", "cap5", "coach5"));
        teamList.add(new Team("team6", "cap6", "coach6"));
        teamList.add(new Team("team7", "cap7", "coach7"));
        teamList.add(new Team("team8", "cap8", "coach8"));
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList() {
        this.teamList = jdbcConfig.teamsToList();
    }

    public void addTeam(Team team) {
        jdbcConfig.saveTeam(team);
    }

    public int getSize() {
        return teamList.size();
    }

    public void removeTeam(Team team) {
        jdbcConfig.removeTeam(team);
    }

}
