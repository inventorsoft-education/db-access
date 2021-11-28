package com.example.demo.repository;

import com.example.demo.entity.Team;
import com.example.demo.service.SQLMethods;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamsList {
    List<Team> teams = new ArrayList<>();
    SQLMethods sqlMethods;

    Team Monday = new Team("Monday", "M", "N");
    Team Tuesday = new Team("Tuesday", "T", "E");
    Team Wednesday = new Team("Wednesday", "W", "D");
    Team Thursday = new Team("Thursday", "H", "R");
    Team Friday = new Team("Friday", "F", "I");
    Team Saturday = new Team("Saturday", "S", "R");
    Team Sunday = new Team("Sunday", "D", "A");

    public void madeList() {
        teams.add(Monday);
        teams.add(Tuesday);
        teams.add(Wednesday);
        teams.add(Thursday);
        teams.add(Friday);
        teams.add(Saturday);
        teams.add(Sunday);
    }

    public Boolean add(Team team) {
        return teams.add(team);
    }

    public int size() {
        return teams.size();
    }

    public Team getTeamByIndex(Integer num) {
        return teams.get(num);
    }

    public void warning() {
        if (teams.size() % 2 == 0) {
            System.out.println(teams.size());
        } else {
            System.out.println("Amount of teams must be even. Please add or drop one team! ");
        }
    }

    public void deleteTeam(Team team) {
        teams.remove(team);
    }

}

