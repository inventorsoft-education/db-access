package co.inventrosoft.hibernate_task.service;

import co.inventrosoft.hibernate_task.console.MatchResult;
import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.model.Tournament;
import co.inventrosoft.hibernate_task.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    /**
     * creates a matches {@link Match} for tournament {@link Tournament},
     * where teams in first round stores teams from list, the rest stores null
     */
    @Transactional
    public void createMatchesInTournament(int tournamentId, List<Team> teams) {
        Tournament tournament = new Tournament(tournamentId);

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
        saveAll(matches);
    }

    /**
     * Finds and sets match's score by {@link MatchResult} object.
     * @param matchResult {@link MatchResult}  stores score and team names
     * @param match {@link Match}  is a match to be updated
     */
    @Transactional
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
            matchRepository.findByRoundCodeAndOrderAndTournamentId(roundCode / 2, order / 2, tournamentId)
                    .ifPresent(nextMatch -> {
                        nextMatch.setTeamByOrder(order, winner);
                        matchRepository.save(nextMatch);
                    });
        }
    }

    /**
     * Finds in storage match by {@link MatchResult}  object.
     * Swaps values in matchResult if match was not found.
     * @param matchResult {@link MatchResult} stores score and team names
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

    @Transactional
    public List<Match> findAllByTournamentId(int tournamentId) {
        return matchRepository.findAllByTournamentId(tournamentId);
    }

    @Transactional
    public void saveAll(List<Match> matches) {
        matchRepository.saveAll(matches);
    }
}
