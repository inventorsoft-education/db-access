package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.dao.intetface.TournamentDAOInterface;
import co.inventorsoft.academy.jdbc.MyJDBC;
import co.inventorsoft.academy.model.Tournament;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Component
public class TournamentDAO implements TournamentDAOInterface {
    private static final String INSERT_TEAM = "INSERT INTO tournament (t_name, match_id, match_date) VALUES (?, ?, ?);";
    private static final String SELECT_LAST = "SELECT t_name,match_id,match_date FROM tournament ORDER BY id DESC LIMIT 1;";
    /**
     * This method add Tournament to database
     *
     * @param tournament input Tournament
     */
    @Override
    public void addTournament(Tournament tournament) {
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEAM)) {
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setInt(2, tournament.getMatch());
            preparedStatement.setDate(3, new Date(tournament.getDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }

    /**
     * This method get Tournament from database by input index
     *
     * @param id input index
     * @return Tournament
     */
    @Override
    public Tournament getTournament(Integer id) {
        return null;
    }

    /**
     * This method delete Tournament from database
     *
     * @param id input Tournament id
     */
    @Override
    public void deleteTournament(Integer id) {

    }

    /**
     * This method update Tournament by id
     *
     * @param id         input id of Tournament
     * @param Tournament new value of Tournament
     */
    @Override
    public void updateTournament(Integer id, Tournament Tournament) {

    }

    public Tournament getFinalMatch(){
        Tournament tournament = null;
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("t_name");
                int winner = rs.getInt("match_id");
                Date date = rs.getDate("match_date");
                tournament = new Tournament(name, winner, date);
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return tournament;
    }
}
