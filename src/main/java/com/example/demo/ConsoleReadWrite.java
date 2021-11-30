package com.example.demo;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleReadWrite {

    static final String YES = "y";
    Tournament tournament;
    Scanner scanner = new Scanner(System.in);

    ConsoleReadWrite(Tournament tournament) {
        this.tournament = tournament;
    }

    public void printMatches() {
        System.out.println("\nRound, Team 1, Team 2, Score");
        List<Match> matches = tournament.getMatchService().matchesToList();
        matches.forEach(match -> System.out.println(match));
    }

    public void printTeams() {
        System.out.println("\nCurrent teams in the tournament");
        System.out.println("    Team,   Captain,    Coach");
        List<Team> teams = tournament.getTeamService().teamsToList();
        teams.forEach(team -> System.out.println(team));
    }

    public void start() {
        if (tournament.getTeamService().teamsToList().isEmpty()) {
            List<Team> teams = tournament.getTeamService().createList();
            tournament.getTeamService().saveTeams(teams);
        }
        if (!tournament.getMatchService().matchesToList().isEmpty()) {
            tournament.getMatchService().removeMatches();
        }
        System.out.println("    WELCOME TO THE TOURNAMENT");
        printTeams();
        String answer;
        System.out.print("\nDo you want to add new teams?  (y / n)");
        answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(YES)) {
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
            tournament.getTeamService().saveTeam(team);
            System.out.println("You've added new team:\n" + team);
            if (!tournament.isPowerOfTwo(tournament.getTeamService().teamsToList().size())) {
                System.out.println("Add more teams");
            } else {
                addNext = false;
            }
        }
    }

}
