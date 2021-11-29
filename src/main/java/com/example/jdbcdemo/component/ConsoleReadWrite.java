package com.example.jdbcdemo.component;

import com.example.jdbcdemo.configuration.JdbcConfig;
import com.example.jdbcdemo.model.Match;
import com.example.jdbcdemo.model.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleReadWrite {

    Tournament tournament;
    JdbcConfig jdbcConfig;
    Scanner scanner = new Scanner(System.in);

    public ConsoleReadWrite(Tournament tournament, JdbcConfig jdbcConfig) {
        this.tournament = tournament;
        this.jdbcConfig = jdbcConfig;
    }

    public void printMatches() {
        System.out.println("\nRound, Team 1, Team 2, Score");
        for (Match match : tournament.getMatchList().getMatchList()) {
            System.out.println(match);
        }
    }

    public void printTeams() {
        System.out.println("\nCurrent teams in the tournament");
        System.out.println("    Team,   Captain,    Coach");
        for (Team team : jdbcConfig.teamsToList()) {
            System.out.println(team);
        }
    }

    public void start() {
        JdbcConfig.deleteMatches();
        tournament.getTeamList().setTeamList();
        System.out.println("    WELCOME TO THE TOURNAMENT");
        printTeams();
        String answer;
        System.out.print("\nDo you want to add new teams?  (y / n)");
        answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            addTeam();
        }
        tournament.generateMatches();
        printMatches();
        System.out.println("\nWINNER - " + tournament.getWinner());
    }

    void addTeam() {
        System.out.println("Enter team information");
        boolean addNext = true;
        while (addNext) {
            Team team = new Team();
            System.out.print("Enter team name - ");
            team.setName(scanner.nextLine());
            System.out.print("Enter team captain - ");
            team.setCapitan(scanner.nextLine());
            System.out.print("Enter team coach - ");
            team.setCoach(scanner.nextLine());
            tournament.getTeamList().addTeam(team);
            jdbcConfig.saveTeam(team);
            System.out.println("You've added new team:\n" + team);
            if (!tournament.isPowerOfTwo(tournament.getTeamList().getSize())) {
                System.out.println("Add more teams");
            } else {
                addNext = false;
            }
        }
    }

}
