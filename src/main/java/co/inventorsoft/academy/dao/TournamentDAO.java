package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.dao.intetface.TournamentDAOInterface;
import co.inventorsoft.academy.jdbc.MyJDBC;
import co.inventorsoft.academy.model.Tournament;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static co.inventorsoft.academy.jdbc.MyJDBC.connection;

@Repository
public class TournamentDAO implements TournamentDAOInterface {
    private static final String INSERT_TEAM = "INSERT INTO tournament (t_name, match_id, match_date) VALUES (?, ?, ?);";
    private static final String SELECT_LAST = "SELECT t_name, match_id, match_date FROM tournament ORDER BY id DESC LIMIT 1;";

    /**
     * This method add {@link Tournament} to database
     *
     * @param tournament input {@link Tournament}
     */
    @Override
    public void addTournament(Tournament tournament) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEAM)) {
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setInt(2, tournament.getMatch());
            preparedStatement.setDate(3, MyJDBC.getSQLDate(tournament.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }

    /**
     * This method select last match == final and return {@link Tournament}
     *
     * @return {@link Tournament}
     */
    public Tournament getFinalMatch() {
        Tournament tournament = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("t_name");
                int winner = rs.getInt("match_id");
                LocalDate date = rs.getDate("match_date").toLocalDate();
                tournament = new Tournament(name, winner, date);
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return tournament;
    }
}
