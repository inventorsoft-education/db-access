package dao;

import entity.Match;
import java.util.List;


public interface MatchDao extends BaseDao<Integer, Match>{
    public List<Match> findByTournamentId(int id);
}
