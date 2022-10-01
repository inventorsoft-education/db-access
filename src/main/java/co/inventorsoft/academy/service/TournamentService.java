package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.dao.TeamDAO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static co.inventorsoft.academy.enums.ColorText.*;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentService {
    /**
     * list with team on this tournament
     */
    TeamDAO teamsList;
    /**
     * file writer service
     */
    FileWriterService fileWriter;

    /**
     * This method create tournament,write result to console and data.csv file
     */
    public void start() {
        System.out.println("*****************************************************" + GREEN.getValue() +
                "Teams list" + RESET.getValue() + "*****************************************************" + PURPLE.getValue());
           teamsList.displayTeams();
        System.out.println(RESET.getValue() + "************************************************************" +
                "********************************************************");
        System.out.println("*****" + GREEN.getValue() + "Round" + RESET.getValue() + "************************************"
                + GREEN.getValue() + "Team 1" + RESET.getValue() + "***************" + GREEN.getValue() + "Team 2" + RESET.getValue()
                + "**********************************" + GREEN.getValue() + "Score" + RESET.getValue() + "****");
        fileWriter.write("Round, Team 1, Team 2, Score");
        while (teamsList.size() != 1) {
            teamsList.generateNewPoints();
            calculateResult(teamsList.size() == 2 ? "Final" : "1/" + teamsList.size() / 2);
            System.out.println("************************************************************" +
                    "********************************************************");
        }
        /* display winner of tournament*/
        winner();
    }

    /**
     * This method display and write to file all matches in current round
     *
     * @param round name of round
     */
    private void calculateResult(String round) {
        int size = teamsList.size();
        for (int i = 0; i < size; i += 2) {
            Team team1 = teamsList.getTeam(size - 1 - i);
            Team team2 = teamsList.getTeam(size - 2 - i);
            fileWriter.write(round + ", " + team1.getName() + ", " + team2.getName() + ", "
                    + team1.getPoints() + ":" + team2.getPoints());
            System.out.println(getWinner(teamsList.getTeam(size - 1 - i),
                    teamsList.getTeam(size - 2 - i), round));
        }
    }

    /**
     * This method calculate winner from a pair of teams
     *
     * @param teamFirst  first team
     * @param teamSecond second team
     * @param round      actual round name
     * @return String value of result
     */
    public String getWinner(Team teamFirst, Team teamSecond, String round) {
        String result = String.format("*" + BLUE.getValue() + "%8s\t" + RESET.getValue() + "   * " + YELLOW.getValue()
                        + "%40s - %-40s" + RESET.getValue() + " * " + CYAN.getValue() + "%5s:%-5s" + RESET.getValue() + " *",
                round, teamFirst.getName(), teamSecond.getName(),
                teamFirst.getPoints(), teamSecond.getPoints());
        /* delete loser team */
        teamsList.deleteTeam(teamFirst.getPoints() > teamSecond.getPoints() ? teamSecond : teamFirst);
        return result;
    }

    /**
     * This method display result of tournament
     */
    public void winner() {
        String winner = teamsList.getTeam(0).getName();
        System.out.println("****************************************************" +
                "****************************************************************");
        System.out.println(GREEN.getValue() + "\t\t\t\t\t\t\t\tThe Winner of tournament is " + winner);
        System.out.println(RESET.getValue() + "****************************************************" +
                "****************************************************************");
    }
}