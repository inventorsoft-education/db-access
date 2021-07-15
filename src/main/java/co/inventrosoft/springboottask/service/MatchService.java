package co.inventrosoft.springboottask.service;

import co.inventrosoft.springboottask.console.MatchResult;
import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.repository.MatchRepositoryJdbcImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepositoryJdbcImpl matchRepository;

    /**
     * Finds and sets match's score by MatchResult object.
     * @param matchResult stores score and team names
     * @param match is a match to be updated
     */
    public void updateMatchResults(MatchResult matchResult, Match match) {
        match.setFirstTeamResult(matchResult.getFirstTeamResult());
        match.setSecondTeamResult(matchResult.getSecondTeamResult());
        match.setPlayed(true);
        matchRepository.update(match);

        int roundCode = match.getRoundCode();
        int order = match.getOrder();

        if (!match.isFinal()) {
            Team winner = match.getWinner();
            // find match where winner should be placed
            Match nextMatch = matchRepository.getByRoundCodeAndOrder(roundCode / 2, order / 2, match.getTournamentId());

            nextMatch.setTeamByOrder(order, winner);
            matchRepository.update(nextMatch);
        }
    }

    public List<Match> getTournament(int tournamentId) {
        return matchRepository.findByTournament(tournamentId);
    }
    public void saveAll(List<Match> matches) {
        matchRepository.save(matches);
    }

    /**
     * Finds in storage match by MatchResult object.
     * Swaps values in matchResult if match was not found.
     * @param matchResult stores score and team names
     */
    public Match getMatchByResult(MatchResult matchResult, int tournamentId) {
        Match match = matchRepository.getByTeamNames(matchResult.getFirstTeamName(), matchResult.getSecondTeamName(), tournamentId);
        if (match == null) {
            matchResult.swap();
            match = matchRepository.getByTeamNames(matchResult.getFirstTeamName(), matchResult.getSecondTeamName(), tournamentId);
        }
        return match;
    }

    public String toCsv(Match match) {
        String str = null;
        if (match.getPlayed()) {
            str = "1/" + match.getRoundCode() + "," + match.getFirstTeam().getName() + ",";
            str += match.getSecondTeam().getName() + "," + match.getScore() + "\n";
        }
        return str;
    }

    public void writeAllToCsv(int tournamentId) {
        List<Match> matches = matchRepository.findByTournament(tournamentId);
        try (PrintWriter writer = new PrintWriter("result.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("Round,Team 1,Team 2,Score\n");
            for (Match match: matches) {
                sb.append(toCsv(match));
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
