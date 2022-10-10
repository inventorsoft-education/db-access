package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Match;
import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.model.Tournament;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static co.inventorsoft.academy.Application.FORMAT_LINE;
import static co.inventorsoft.academy.enums.ColorText.BLUE;
import static co.inventorsoft.academy.enums.ColorText.CYAN;
import static co.inventorsoft.academy.enums.ColorText.GREEN;
import static co.inventorsoft.academy.enums.ColorText.PURPLE;
import static co.inventorsoft.academy.enums.ColorText.RESET;
import static co.inventorsoft.academy.enums.ColorText.YELLOW;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainService {

    TeamService teamService;

    MatchService matchService;

    TournamentService tournamentService;

    /**
     * This method create tournament,write result to console
     */
    public void start() {
        String nameOfTournament = "Derby-" + System.currentTimeMillis();
        List<Team> teams = teamService.getTeams();
        System.out.println("*****************************************************"
                + GREEN.getValue() + "Teams list" + RESET.getValue() + "*********"
                + "********************************************" + PURPLE.getValue());
        teams.stream()
                .map(team -> String.format("Team: %s with pilots: %s and %s",
                team.getName(), team.getPilot1(), team.getPilot2()))
                .forEach(System.out::println);
        System.out.println(RESET.getValue() + FORMAT_LINE);
        System.out.println("*****"
                + GREEN.getValue() + "Round"
                + RESET.getValue() + "************************************"
                + GREEN.getValue() + "Team 1"
                + RESET.getValue() + "***************"
                + GREEN.getValue() + "Team 2"
                + RESET.getValue() + "**********************************"
                + GREEN.getValue() + "Score"
                + RESET.getValue() + "****");
        while (teams.size() != 1) {
            Collections.shuffle(teams);
            String roundName = teams.size() == 2
                    ? "Final"
                    : "1/" + teams.size() / 2;
            calculateResult(teams, nameOfTournament, roundName);
            System.out.println(FORMAT_LINE);
        }
        winner();
    }

    /**
     * This method add {@link Match} and display all matches in current round
     */
    private void calculateResult(List<Team> teams, String name, String round) {
        int size = teams.size();
        for (int i = 0; i < size; i += 2) {
            Team team1 = teams.get(size - 1 - i);
            Team team2 = teams.get(size - 2 - i);
            int score1 = (int) (Math.random() * 10);
            int score2 = (int) (Math.random() * 10);
            Match match = new Match(round, team1, team2, score1,score2);
            matchService.createMatch(match);
            tournamentService.createTournament(new Tournament(name, match, LocalDate.now()));
            System.out.print(String.format("""
                    *\u001B[34m%8s\t\u001B[0m   \
                    * \u001B[33m%40s - %-40s\u001B[0m \
                    * \u001B[36m%5s:%-5s\u001B[0m *
                    """,
                    match.getRound(),
                    team1.getName(),
                    team2.getName(),
                    match.getPointsTeam1(),
                    match.getPointsTeam2()));
            Team loser = match.getPointsTeam1() > match.getPointsTeam2() ? team2 : team1;
            teams.remove(loser);
        }
    }

    /**
     * This method display winner of tournament
     */
    public void winner() {
        List<String> result = tournamentService.getLastWinner();
        System.out.println(FORMAT_LINE);
        System.out.println(GREEN.getValue()
                + "\t\t\tThe Winner of tournament "
                + result.get(0) + " is "
                + result.get(1) + " in "
                + result.get(2));
        System.out.println(RESET.getValue() + FORMAT_LINE);
    }
}