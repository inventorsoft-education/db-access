package co.inventrosoft.springboottask.service;

import co.inventrosoft.springboottask.console.MatchResult;
import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.repository.TeamRepositoryJdbcImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepositoryJdbcImpl teamRepository;

    public void save(List<Team> teams) throws IOException {
        teamRepository.save(teams);
    }

    public boolean isTeamExist(String teamName) {
        return teamRepository.isExist(teamName);
    }

    public boolean areTeamsInMatchResultExists(MatchResult matchResult) {
        return isTeamExist(matchResult.getFirstTeamName()) && isTeamExist(matchResult.getSecondTeamName());
    }
}
