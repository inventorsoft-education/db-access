package co.inventrosoft.hibernate_task;


import co.inventrosoft.hibernate_task.console.ConsoleParser;
import co.inventrosoft.hibernate_task.console.MatchResult;
import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.service.MatchService;
import co.inventrosoft.hibernate_task.service.TeamService;
import co.inventrosoft.hibernate_task.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@SpringBootApplication(proxyBeanMethods = false)
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class SpringBootTaskApplication implements CommandLineRunner {
    private final TournamentService tournamentService;
    private final MatchService matchService;
    private final TeamService teamService;
    private final ConsoleParser consoleParser;

    private void start() throws IOException {
        // step 1: get teams
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Team> teams = consoleParser.getTeams(reader);
        teamService.save(teams);
        Collections.shuffle(teams);

        // step 2: create tournament
        int tournamentId = tournamentService.createTournament();
        matchService.createMatchesInTournament(tournamentId, teams);
        Team winner = null;
        do {
            for (Match match : matchService.findAllByTournamentId(tournamentId)) {
                consoleParser.printMatch(match);
            }

            MatchResult matchResult = consoleParser.getResultOfMatch(reader);
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
            matchService.updateMatchResults(matchResult, match, tournamentId);
            if (match.isFinal()) {
                winner = match.getWinner();
            }
        } while (winner == null);
        consoleParser.printWinner(winner);
        reader.close();
    }

    @Override
    public void run(String... args) throws Exception {
        start();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);
    }

}
