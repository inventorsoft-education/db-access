package co.inventrosoft.springboottask.repository;

import co.inventrosoft.springboottask.model.Match;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Repository
@AllArgsConstructor
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
        List<Match> matches = findByTournament(tournamentId);
        Match resultMatch = null;
        for (Match match: matches) {
            boolean checkIfTeamsNotNulls = match.getFirstTeam() != null && match.getSecondTeam() != null;
            if (checkIfTeamsNotNulls && match.getFirstTeam().getName().equals(firstTeamName)
                    && match.getSecondTeam().getName().equals(secondTeamName)) {
                resultMatch = match;
                break;
            }
        }
        return resultMatch;
    }

    @Override
    public Match getByRoundCodeAndOrder(int roundCode, int order, int tournamentId) {
        List<Match> matches = findByTournament(tournamentId);
        Match resultMatch = null;
        for (Match match: matches) {
            if (match.getRoundCode() == roundCode && match.getOrder() == order) {
                resultMatch = match;
                break;
            }
        }
        return resultMatch;
    }

    @Override
    public void update(Match match) {
        List<Match> matches = findByTournament(0);
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getRoundCode() == match.getRoundCode() && matches.get(i).getOrder() == match.getOrder()) {
                matches.set(i, match);
                save(matches);
                break;
            }
        }
    }
}
