package co.inventrosoft.hibernate_task.repository;

import co.inventrosoft.hibernate_task.model.Match;

import java.util.List;

public interface MatchRepository {
    List<Match> findAll(int tournamentId);
    Match getByTeamNames(String firstTeam, String secondTeam, int tournamentId);
    Match getByRoundCodeAndOrder(int roundCode, int order, int tournamentId);

    void save(List<Match> matches);
    void update(Match match);
}
