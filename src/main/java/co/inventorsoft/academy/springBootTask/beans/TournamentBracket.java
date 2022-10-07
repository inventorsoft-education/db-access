package co.inventorsoft.academy.springBootTask.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
@RequiredArgsConstructor
public class TournamentBracket {

    @Autowired
    Team team;

    public static final int MINIMUM_TEAMS_NUMBER = 4;

    public List<Team> addTeams() {
        List<Team> teams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isActiveScanner = true;
        while (isActiveScanner) {
            System.out.println("Amount of teams: " + teams.size());
            System.out.println("Enter team (name, capitan, coach)");
            System.out.println("Enter 'stop' to finish adding teams");
            String currentLine = scanner.nextLine();
            if (currentLine.equals("stop")) {
                if (isPowerOfTwo(teams.size()) && teams.size() >= MINIMUM_TEAMS_NUMBER) {
                    isActiveScanner = false;
                } else {
                    System.out.println("Amount of teams should be power of 2 and at least 4");
                }

            } else {
                Team currentTeam = addTeam(currentLine);
                if (currentTeam != null) {
                    teams.add(currentTeam);
                }
            }
        }
        Collections.shuffle(teams);
        return teams;
    }

    private Team addTeam(String currentLine) {
        String[] teamDetails = currentLine.split(", ");
        if (teamDetails.length != 3) {
            System.out.println("Invalid input");
            return null;
        } else {
            team = new Team(teamDetails[0], teamDetails[1], teamDetails[2]);
        }
        System.out.println("team added");
        return team;
    }

    private boolean isPowerOfTwo(int number) {
        return Integer.bitCount(number) == 1;
    }

    public void makeResults(List<Team> teams) {
        int numberOfTeams = teams.size();
        int numberOfRounds = (int) log2(numberOfTeams);
        List<Team> currentTeams = teams;
        for (int i = 0; i < numberOfRounds; i++) {
            currentTeams = playRound(currentTeams);
        }
        System.out.println("WINNER: " + currentTeams.get(0).toString());
    }

    private List<Team> playRound(List<Team> teams) {
        List<Team> winners = new ArrayList<>();
        for (int i = 0; i <= teams.size() - 2; i += 2) {
            Team team1 = teams.get(i);
            Team team2 = teams.get(i + 1);
            int score1 = (int) (Math.random() * 5);
            int score2 = (int) (Math.random() * 5);
            while (score1 == score2) {
                score2 = (int) (Math.random() * 5);
            }
            if (score1 > score2) {
                winners.add(team1);
            } else {
                winners.add(team2);
            }
            System.out.println("1/" + teams.size() / 2 + ", " + team1.getName() +
                    ", " + team2.getName() + ", " + score1 + ":" + score2);
        }
        return winners;
    }

    private double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

}
