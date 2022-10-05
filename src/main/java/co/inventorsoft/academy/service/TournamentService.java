package co.inventorsoft.academy.service;

import co.inventorsoft.academy.dao.MatchDAO;
import co.inventorsoft.academy.dao.TeamDAO;
import co.inventorsoft.academy.dao.TournamentDAO;
import co.inventorsoft.academy.model.Match;
import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.model.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static co.inventorsoft.academy.enums.ColorText.BLUE;
import static co.inventorsoft.academy.enums.ColorText.CYAN;
import static co.inventorsoft.academy.enums.ColorText.GREEN;
import static co.inventorsoft.academy.enums.ColorText.PURPLE;
import static co.inventorsoft.academy.enums.ColorText.RESET;
import static co.inventorsoft.academy.enums.ColorText.YELLOW;

@Service
@RequiredArgsConstructor
public class TournamentService {
    /**
     * database of teams on tournaments
     */
    private final TeamDAO teamDAO;
    /**
     * database of matches on tournaments
     */
    private final MatchDAO matchDAO;

    /**
     * database of tournaments
     */
    private final TournamentDAO tournamentDAO;


    /**
     * This method create tournament,write result to console
     */
    public void start() {
        String nameOfTournament = "Derby-" + System.currentTimeMillis();
        List<Team> teams = teamDAO.getTeams();
        System.out.println("*****************************************************" + GREEN.getValue() + "Teams list" + RESET.getValue() + "*****************************************************" + PURPLE.getValue());
        teams.forEach(System.out::println);
        System.out.println(RESET.getValue() + "********************************************************************************************************************");
        System.out.println("*****" + GREEN.getValue() + "Round" + RESET.getValue() + "************************************" + GREEN.getValue() + "Team 1" + RESET.getValue() + "***************" + GREEN.getValue() + "Team 2" + RESET.getValue() + "**********************************" + GREEN.getValue() + "Score" + RESET.getValue() + "****");
        while (teams.size() != 1) {
            Collections.shuffle(teams);
            calculateResult(teams, nameOfTournament, teams.size() == 2 ? "Final" : "1/" + teams.size() / 2);
            System.out.println("********************************************************************************************************************");
        }
        winner();
    }

    /**
     * This method add match and display all matches in current round
     */
    private void calculateResult(List<Team> teams, String name, String round) {
        int size = teams.size();
        for (int i = 0; i < size; i += 2) {
            Team team1 = teams.get(size - 1 - i);
            Team team2 = teams.get(size - 2 - i);
            Match match = new Match(round, teamDAO.getId(team1), teamDAO.getId(team2), (int) (Math.random() * 10), (int) (Math.random() * 10));
            tournamentDAO.addTournament(new Tournament(name, matchDAO.addMatch(match), LocalDate.now()));
            System.out.printf("*" + BLUE.getValue() + "%8s\t" + RESET.getValue() + "   * " + YELLOW.getValue() + "%40s - %-40s" + RESET.getValue() + " * " + CYAN.getValue() + "%5s:%-5s" + RESET.getValue() + " *%n",
                    match.getRound(), team1.getName(), team2.getName(), match.getPointsTeam1(), match.getPointsTeam2());
            teams.remove(match.getPointsTeam1() > match.getPointsTeam2() ? team2 : team1);
        }
    }

    /**
     * This method display winner of tournament
     */
    public void winner() {
        Tournament tournament = tournamentDAO.getFinalMatch();
        Match match = matchDAO.getMatch(tournament.getMatch());
        String winner = (match.getPointsTeam1() > match.getPointsTeam2()
                ? teamDAO.getTeam(match.getTeam1()).getName()
                : teamDAO.getTeam(match.getTeam2()).getName());
        System.out.println("********************************************************************************************************************");
        System.out.println(GREEN.getValue() + "\t\t\tThe Winner of tournament " + tournament.getName() + " is " + winner + " in " + tournament.getDate());
        System.out.println(RESET.getValue() + "********************************************************************************************************************");
    }
}