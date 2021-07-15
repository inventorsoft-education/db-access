package co.inventrosoft.hibernate_task.service;

import co.inventrosoft.hibernate_task.console.MatchResult;
import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    /**
     * Finds and sets match's score by MatchResult object.
     * @param matchResult stores score and team names
     * @param match is a match to be updated
     */
    public void updateMatchResults(MatchResult matchResult, Match match, int tournamentId) {
        match.setFirstTeamResult(matchResult.getFirstTeamResult());
        match.setSecondTeamResult(matchResult.getSecondTeamResult());
        match.setPlayed(true);
        matchRepository.save(match);

        int roundCode = match.getRoundCode();
        int order = match.getOrder();

        if (!match.isFinal()) {
            Team winner = match.getWinner();
            // find match where winner should be placed
            Optional<Match> nextMatchOpt = matchRepository.findByRoundCodeAndOrderAndTournamentId(
                    roundCode / 2, order / 2, tournamentId
            );
            if (nextMatchOpt.isPresent()) {
                Match nextMatch = nextMatchOpt.get();
                nextMatch.setTeamByOrder(order, winner);
                matchRepository.save(nextMatch);
            }
        }
    }

    /**
     * Finds in storage match by MatchResult object.
     * Swaps values in matchResult if match was not found.
     * @param matchResult stores score and team names
     */
    public Match getMatchByResult(MatchResult matchResult, int tournamentId) {
        Match match = matchRepository.getByTeamNamesAndTournamentId(
                matchResult.getFirstTeamName(), matchResult.getSecondTeamName(), tournamentId
        );
        if (match == null) {
            matchResult.swap();
            match = matchRepository.getByTeamNamesAndTournamentId(
                    matchResult.getFirstTeamName(), matchResult.getSecondTeamName(), tournamentId
            );
        }
        return match;
    }

    public List<Match> findAllByTournamentId(int tournamentId) {
        return matchRepository.findAllByTournamentId(tournamentId);
    }

    public void saveAll(List<Match> matches) {
        matchRepository.saveAll(matches);
    }
}
