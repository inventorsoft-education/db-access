package co.inventrosoft.hibernate_task.service;

import co.inventrosoft.hibernate_task.console.MatchResult;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public void save(List<Team> teams) {
        teamRepository.saveAll(teams);
    }

    public boolean isTeamExist(String teamName) {
        return teamRepository.existsByName(teamName);
    }

    public boolean areTeamsInMatchResultExists(MatchResult matchResult) {
        return isTeamExist(matchResult.getFirstTeamName())
                && isTeamExist(matchResult.getSecondTeamName());
    }
}
