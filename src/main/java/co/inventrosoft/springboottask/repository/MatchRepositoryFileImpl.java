package co.inventrosoft.springboottask.repository;

import co.inventrosoft.springboottask.model.Match;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MatchRepositoryFileImpl implements MatchRepository {
    private static final String MATCHES_FILE = "matches.json";
    private final ObjectMapper mapper;

    @Override
    public void save(List<Match> matches) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(MATCHES_FILE, false)))) {
            mapper.writeValue(out, matches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Match> findByTournament(int tournamentId) {
        List<Match> matches = new ArrayList<>();
        try {
            matches = mapper.readValue(new File(MATCHES_FILE), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    @Override
    public Match getByTeamNames(String firstTeamName, String secondTeamName, int tournamentId) {
        return findByTournament(tournamentId).stream()
                .filter(match -> firstTeamName.equals(match.getFirstTeamName())
                        && secondTeamName.equals(match.getFirstTeamName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Match getByRoundCodeAndOrder(int roundCode, int order, int tournamentId) {
        return findByTournament(tournamentId).stream()
                .filter(match -> match.getRoundCode() == roundCode && match.getOrder() == order)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Match matchData) {
        List<Match> matches = findByTournament(matchData.getTournamentId());
        matches.stream()
                .filter(match -> matchData.getRoundCode() == match.getRoundCode()
                        && matchData.getOrder() == match.getOrder())
                .findFirst()
                .ifPresent(match -> {
                    match.setTournamentId(matchData.getTournamentId());
                    match.setRoundCode(matchData.getRoundCode());
                    match.setOrder(matchData.getOrder());
                    match.setFirstTeam(matchData.getFirstTeam());
                    match.setSecondTeam(matchData.getSecondTeam());
                    match.setFirstTeamResult(matchData.getFirstTeamResult());
                    match.setSecondTeamResult(matchData.getSecondTeamResult());
                    match.setPlayed(matchData.getPlayed());

                    save(matches);
                });

    }
}
