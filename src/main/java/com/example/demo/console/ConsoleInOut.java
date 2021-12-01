package com.example.demo.console;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.tournament.Tournament;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleInOut {
    Tournament tournament;
    Scanner in;

    public void outTeam(Team team) {
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

    @Transactional(readOnly = true)
    public void outMatchList() {
        System.out.println("List of matches:\n");
        for (Match match : tournament.getFullMatchList()) {
            System.out.println("First team name: " + match.getFirstTeam().getName());
            System.out.println("Second team name: " + match.getSecondTeam().getName());
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

    public void outWinner() {
        System.out.println("The winner of tournament: ");
        outTeam(tournament.getWinner());
    }


}
