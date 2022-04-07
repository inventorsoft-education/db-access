package dao.impl;

import dao.TeamDao;
import entity.Team;
import jdbc.JdbcUtils;
import lombok.AllArgsConstructor;
import mapper.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class TeamDaoImpl implements TeamDao {
    JdbcUtils jdbcUtils;

    @Override
    public Team find(Integer id) {
        final String SQL_FIND_MATCH = "SELECT * FROM TEAMS WHERE ID=?";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_MATCH);
            statement.setInt(1, id);
            List<Team> result = ResultSetMapper.teamList(statement.executeQuery());
            statement.close();
            if (result.isEmpty()) return null;
            else return result.get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Team value) {
        final String SQL_ADD_MATCH = "INSERT INTO TEAMS VALUES(?,?,?,?) RETURNING TRUE";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_MATCH);
            statement.setInt(1, value.getId());
            statement.setString(2, value.getName());
            statement.setString(3, value.getCoach());
            statement.setString(4, value.getCaptain());
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Team value, Integer id) {
        final String SQL_UPDATE_MATCH = "UPDATE TEAMS SET TEAMNAME=?, COACH=?, CAPTAIN=?" +
                "WHERE ID=? RETURNING TRUE";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MATCH);
            statement.setInt(4, id);
            statement.setString(1, value.getName());
            statement.setString(2, value.getCoach());
            statement.setString(3, value.getCaptain());
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
        final String SQL_DELETE_MATCH = "DELETE FROM TEAMS WHERE ID=? RETURNING TRUE";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MATCH);
            statement.setInt(1, id);
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Team> findByMatchId(int id) {
        final String SQL_FIND_BY_MATCHID = "SELECT * FROM TEAMS INNER JOIN MATCHES ON MATCHES.FIRSTTEAMID=TEAMS.ID WHERE MATCHES.ID=?" +
                "UNION SELECT * FROM TEAMS INNER JOIN MATCHES ON MATCHES.SECONDTEAMID=TEAMS.ID WHERE MATCHES.ID=?";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MATCHID);
            statement.setInt(1, id);
            statement.setInt(2, id);
            List<Team> result = ResultSetMapper.teamList(statement.executeQuery());
            statement.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Team> findByTournamentId(int id) {
        final String SQL_FIND_BY_TOURNAMENTID = "SELECT * FROM TEAMS INNER JOIN MATCHES ON MATCHES.FIRSTTEAMID=TEAMS.ID OR MATCHES.SECONDTEAMID=TEAMS.ID " +
                "INNER JOIN TOURNAMENTS ON TOURNAMENTS.ID=MATCHES.TOURNAMENTID WHERE TOURNAMENTS.ID=?";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_TOURNAMENTID);
            statement.setInt(1, id);
            List<Team> result = ResultSetMapper.teamList(statement.executeQuery());
            statement.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
