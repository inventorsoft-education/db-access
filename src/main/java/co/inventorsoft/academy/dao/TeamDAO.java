package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.model.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class TeamDAO{
    /**
     * The list of teams
     */
    private final List<Team> teams = new ArrayList<>();

    /**
     * This method add team to list
     *
     * @param team new team
     */
    public void add(Team team) {
        teams.add(team);
    }

    /**
     * This method return size of list
     *
     * @return size of list
     */
    public int size() {
        return teams.size();
    }

    /**
     * This method get team from list by input index
     *
     * @param num input index
     * @return team from list
     */
    public Team getTeam(Integer num) {
        return teams.get(num);
    }

    /**
     * This method random shuffle team and display list of teams
     */
    public void displayTeams() {
        Collections.shuffle(teams, new Random());
        teams.forEach(System.out::println);
    }

    /**
     * This method delete team from list
     *
     * @param team input team
     */
    public void deleteTeam(Team team) {
        teams.remove(team);
    }

    /**
     * This method generate new value of point in next round
     */
    public void generateNewPoints() {
        teams.forEach(team -> team.setPoints((int) (Math.random() * 10)));
    }
}

