package dao;

import entity.Team;

import java.util.List;

public interface TeamDao extends BaseDao<Integer, Team> {
    public List<Team> findByMatchId(int id);
    public List<Team> findByTournamentId(int id);
}
