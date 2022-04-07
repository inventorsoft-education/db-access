package dao.impl;

import dao.MatchDao;
import entity.Match;
import jdbc.JdbcUtils;
import lombok.AllArgsConstructor;
import mapper.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class MatchDaoImpl implements MatchDao {

    private JdbcUtils jdbcUtils;

    @Override
    public Match find(Integer id) {
        final String SQL_FIND_MATCH="SELECT * FROM MATCHES WHERE ID=?";
        try(Connection connection=jdbcUtils.getConnection()){
            PreparedStatement statement =connection.prepareStatement(SQL_FIND_MATCH);
            statement.setInt(1,id);
            List<Match> result= ResultSetMapper.matchList(statement.executeQuery());
            statement.close();
            if(result.isEmpty()) return null;
            else return result.get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Match value) {
        final String SQL_ADD_MATCH="INSERT INTO MATCHES VALUES(?,?,?,?,?,?) RETURNING TRUE";
        try(Connection connection=jdbcUtils.getConnection()){
            PreparedStatement statement =connection.prepareStatement(SQL_ADD_MATCH);
            statement.setInt(1,value.getId());
            statement.setInt(2,value.getFirstTeamId());
            statement.setInt(3,value.getSecondTeamId());
            statement.setInt(4,value.getTournamentId());
            statement.setInt(5,value.getRound());
            statement.setString(6,value.getMatchResult());
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Match value, Integer id) {
        final String SQL_UPDATE_MATCH="UPDATE MATCHES SET TOURNAMENTID=?, FIRSTTEAMID=?, SECONDTEAMID=?," +
                " ROUND=?, MATCHRESULT=? WHERE ID=? RETURNING TRUE";
        try(Connection connection=jdbcUtils.getConnection()){
            PreparedStatement statement =connection.prepareStatement(SQL_UPDATE_MATCH);
            statement.setInt(6,id);
            statement.setInt(2,value.getFirstTeamId());
            statement.setInt(3,value.getSecondTeamId());
            statement.setInt(1,value.getTournamentId());
            statement.setInt(4,value.getRound());
            statement.setString(5,value.getMatchResult());
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        final String SQL_DELETE_MATCH="DELETE FROM MATCHES WHERE ID=? RETURNING TRUE";
        try(Connection connection=jdbcUtils.getConnection()){
            PreparedStatement statement =connection.prepareStatement(SQL_DELETE_MATCH);
            statement.setInt(1,id);
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Match> findByTournamentId(int id) {
        final String SQL_FIND_BY_TOURNAMENT_MATCH="SELECT * FROM MATCHES INNER JOIN TOURNAMENTS ON" +
                " TOURNAMENTS.ID=MATCHES.TOURNAMENTID WHERE TOURNAMENTS.ID=? ";
        try(Connection connection=jdbcUtils.getConnection()){
            PreparedStatement statement =connection.prepareStatement(SQL_FIND_BY_TOURNAMENT_MATCH);
            statement.setInt(1,id);
            return ResultSetMapper.matchList(statement.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
