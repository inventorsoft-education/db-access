package dao.impl;

import dao.TournamentDao;
import entity.Tournament;
import jdbc.JdbcUtils;
import lombok.AllArgsConstructor;
import mapper.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class TournamentDaoImpl implements TournamentDao {

    private JdbcUtils jdbcUtils;

    @Override
    public Tournament find(Integer id) {
        final String SQL_FIND_TOURNAMENT = "SELECT * FROM TOURNAMENTS WHERE ID=?";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURNAMENT);
            statement.setInt(1, id);
            List<Tournament> result = ResultSetMapper.tournamentList(statement.executeQuery());
            statement.close();
            if (result.isEmpty()) return null;
            else return result.get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Tournament value) {
        final String SQL_ADD_TOURNAMENT = "INSERT INTO TOURNAMENTS(ID, ROUNDS) VALUES(?,?) RETURNING TRUE";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_TOURNAMENT);
            statement.setInt(2, value.getNumberOfRounds());
            statement.setInt(1, value.getId());
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Tournament value, Integer id) {
        final String SQL_UPDATE_TOURNAMENT = "UPDATE TOURNAMENTS SET ROUNDS=? WHERE ID=? RETURNING TRUE";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TOURNAMENT);
            statement.setInt(1, value.getNumberOfRounds());
            statement.setInt(2, id);
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
        final String SQL_DELETE_TOURNAMENT = "DELETE FROM TOURNAMENTS WHERE ID=? RETURNING TRUE";
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TOURNAMENT);
            statement.setInt(1, id);
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}


