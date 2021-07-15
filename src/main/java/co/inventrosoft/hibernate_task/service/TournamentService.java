package co.inventrosoft.hibernate_task.service;

import co.inventrosoft.hibernate_task.console.ConsoleParser;
import co.inventrosoft.hibernate_task.console.MatchResult;
import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.model.Tournament;
import co.inventrosoft.hibernate_task.repository.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class TournamentService {
    private final MatchService matchService;
    private final TeamService teamService;

    private final TournamentRepository tournamentRepository;

    private final ConsoleParser consoleParser;

    /**
     * creates a tournament with matches,
     * where teams in first round stores teams from list, the rest stores null
     */
    public Tournament createEmptyTournament(List<Team> teams) {
        Tournament tournament = new Tournament();
        tournamentRepository.save(tournament);

        int teamCount = teams.size();
        int roundCount = (int)(Math.log(teamCount) / Math.log(2)); // log base 2

        List<Match> matches = new ArrayList<>();

        for (int round = 0; round < roundCount; round++) {

            // teamCount / (2 ^ (round+1))
            // this is code of round. e.g code == 2 -> round name = 1/2
            int numOfMatchesInRound = (int)(teamCount / (Math.pow(2, round + 1)));

            for (int matchOrder = 0; matchOrder < numOfMatchesInRound; matchOrder++) {
                Match match = new Match(numOfMatchesInRound, matchOrder, tournament);
                // if first round set teams to matches
                if (round == 0) {
                    match.setFirstTeam(teams.get(matchOrder * 2));
                    match.setSecondTeam(teams.get(matchOrder * 2 + 1));
                }
                matches.add(match);
            }
        }
        matchService.saveAll(matches);

        return tournamentRepository.save(tournament);
    }
    @Transactional
    public void start() throws IOException {
        // step 1: get teams
        List<Team> teams = consoleParser.getTeams();
        teamService.save(teams);
        Collections.shuffle(teams);

        // step 2: create tournament
        Tournament tournament = createEmptyTournament(teams);
        Team winner = null;
        do {
            for (Match match : matchService.findAllByTournamentId(tournament.getId())) {
                consoleParser.printMatch(match);
            }

            MatchResult matchResult = consoleParser.getResultOfMatch();
            if (!teamService.areTeamsInMatchResultExists(matchResult)) {
                consoleParser.printLine("Teams are wrong!");
                continue;
            }
            Match match = matchService.getMatchByResult(matchResult, tournament.getId());
            if (match == null) {
                consoleParser.printLine("Match does not exist!");
                continue;
            }
            if (match.getPlayed()) {
                consoleParser.printLine("Match was played already!");
                continue;
            }
            // update match here
            matchService.updateMatchResults(matchResult, match, tournament.getId());
            if (match.isFinal()) {
                winner = match.getWinner();
            }
        } while (winner == null);
        consoleParser.printWinner(winner);
        consoleParser.close();
    }
}
