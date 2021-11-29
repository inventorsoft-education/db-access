package com.example.demo.console;

import com.example.demo.dao.MatchDAO;
import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.tournament.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class ConsoleInOut {
    @Getter
    private final Tournament tournament;
    private final Scanner in;


    public void outTeam(Team team){
        System.out.println("Team name: " + team.getName());
        System.out.println("Team capitan: " + team.getCaptain());
        System.out.println("Team coach: " + team.getCoach());
    }

    public void outTeamList(List<Team> teams) {
        System.out.println("List of teams:\n");
        for (Team team : teams) {
            System.out.println("Team name: " + team.getName());
            System.out.println("Team capitan: " + team.getCaptain());
            System.out.println("Team coach: " + team.getCoach() + "\n");

        }
    }

    public void outMatchList(){
        System.out.println("List of matches:\n");
        for (Match match : MatchDAO.getList()) {
            System.out.println("First team name: " + match.getFirstTeam());
            System.out.println("Second team name: " + match.getSecondTeam());
            System.out.println("Round: " + match.getRound());
            System.out.println("Score: " + match.getScore());
        }
    }

    public Team inTeam() {
        Team team = new Team();
        System.out.println("Enter the team name: ");
        team.setName(in.nextLine());
        System.out.println("Enter the team captain: ");
        team.setCaptain(in.nextLine());
        System.out.println("Enter the team coach: ");
        team.setCoach(in.nextLine());
        return team;
    }

    public void outMatch(Match match) {
        System.out.println(match.getRound());
        System.out.println(match.getFirstTeam());
        System.out.println(match.getSecondTeam());
        System.out.println(match.getScore());
    }

    public void outMatch(String firstTeam, String secondTeam) {
        boolean flag = false;
        for (Match match : MatchDAO.getList()) {
            if (match.getFirstTeam().equals(firstTeam) &&
                    match.getSecondTeam().equals(secondTeam)) {
                flag = true;
                System.out.println("Round, Team 1, Team 2, Score");
                outMatch(match);
            }
        }
        if (!flag) System.out.println("No match found");
    }

    public void outMatch() {
        String first, second;
        System.out.println("Enter name of the first team");
        first = in.nextLine();
        System.out.println("Enter name of the second team");
        second = in.nextLine();
        outMatch(first, second);
    }

    public void outWinner(){
        System.out.println("The winner of tournament: " );
        outTeam(tournament.getWinner());
    }


}
