package co.inventrosoft.springboottask;


import co.inventrosoft.springboottask.console.ConsoleParser;
import co.inventrosoft.springboottask.console.MatchResult;
import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.service.MatchService;
import co.inventrosoft.springboottask.service.TeamService;
import co.inventrosoft.springboottask.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class SpringBootTaskApplication implements CommandLineRunner {
    private final TournamentService tournamentService;
    private final TeamService teamService;
    private final MatchService matchService;
    private final ConsoleParser consoleParser;

    private void start() throws IOException {
        // step 1: get teams
        List<Team> teams = consoleParser.getTeams();
        teamService.save(teams);
        Collections.shuffle(teams);

        // step 2: create tournament
        int tournamentId = tournamentService.createTournament();
        matchService.createMatchesInTournament(tournamentId, teams);
        Team winner = null;
        do {
            for (Match match : matchService.getTournament(tournamentId)) {
                consoleParser.printMatch(match);
            }

            MatchResult matchResult = consoleParser.getResultOfMatch();
            if (!teamService.areTeamsInMatchResultExists(matchResult)) {
                consoleParser.printLine("Teams are wrong!");
                continue;
            }
            Match match = matchService.getMatchByResult(matchResult, tournamentId);
            if (match == null) {
                consoleParser.printLine("Match does not exist!");
                continue;
            }
            if (match.getPlayed()) {
                consoleParser.printLine("Match was played already!");
                continue;
            }
            // update match here
            matchService.updateMatchResults(matchResult, match);
            if (match.isFinal()) {
                winner = match.getWinner();
            }
        } while (winner == null);
        consoleParser.printWinner(winner);
        consoleParser.close();
        matchService.writeAllToCsv(tournamentId);
    }

    @Override
    public void run(String... args) throws Exception {
        start();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);
    }

}
