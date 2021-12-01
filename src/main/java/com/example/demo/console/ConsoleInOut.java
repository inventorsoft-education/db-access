package com.example.demo.console;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.tournament.Tournament;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleInOut {
    Tournament tournament;
    Scanner in;

    public void printTeam(Team team){
        System.out.println("Team name: " + team.getName());
        System.out.println("Team captain: " + team.getCaptain());
        System.out.println("Team coach: " + team.getCoach());
    }

    public void printTeamList(List<Team> teams) {
        System.out.println("List of teams:\n");
        for (Team team : teams) {
            System.out.println("Team name: " + team.getName());
            System.out.println("Team captain: " + team.getCaptain());
            System.out.println("Team coach: " + team.getCoach() + "\n");
        }
    }

    public void printMatchList(List<Match> matches){
        System.out.println("List of matches:\n");
        for (Match match : matches) {
            System.out.println("First team name: " + match.getFirstTeam());
            System.out.println("Second team name: " + match.getSecondTeam());
            System.out.println("Round: " + match.getRound());
            System.out.println("Score: " + match.getScore());
        }
    }

    public Team inputTeam() {
        Team team = new Team();
        System.out.println("Enter the team name: ");
        team.setName(in.nextLine());
        System.out.println("Enter the team captain: ");
        team.setCaptain(in.nextLine());
        System.out.println("Enter the team coach: ");
        team.setCoach(in.nextLine());
        return team;
    }

    public void printWinner(){
        System.out.println("The winner of tournament: " );
        printTeam(tournament.getWinner());
    }
}