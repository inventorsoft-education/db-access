package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.dao.intetface.MatchDAOInterface;
import co.inventorsoft.academy.jdbc.MyJDBC;
import co.inventorsoft.academy.model.Match;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static co.inventorsoft.academy.jdbc.MyJDBC.connection;

@Repository
public class MatchDAO implements MatchDAOInterface {
    private static final String INSERT_MATCH = "INSERT INTO matches (round, team1, team2, points_team1, points_team2) VALUES (?, ?, ?, ?, ?) RETURNING id;";
    private static final String SELECT_MATCH_BY_ID = "SELECT round, team1, team2, points_team1, points_team2 FROM matches WHERE id = ?;";

    /**
     * This method add {@link Match} to database
     *
     * @param match input {@link Match}
     */
    @Override
    public int addMatch(Match match) {
        int result = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATCH)) {
            preparedStatement.setString(1, match.getRound());
            preparedStatement.setInt(2, match.getTeam1());
            preparedStatement.setInt(3, match.getTeam2());
            preparedStatement.setInt(4, match.getPointsTeam1());
            preparedStatement.setInt(5, match.getPointsTeam2());
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()) {
                    result = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return result;
    }

    /**
     * This method get {@link Match} from database by input index
     *
     * @param id input index
     * @return {@link Match}
     */
    @Override
    public Match getMatch(Integer id) {
        Match match = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATCH_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String round = rs.getString("round");
                    int team1 = rs.getInt("team1");
                    int team2 = rs.getInt("team2");
                    int score1 = rs.getInt("points_team1");
                    int score2 = rs.getInt("points_team2");
                    match = new Match(round, team1, team2, score1, score2);
                }
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return match;
    }
}

