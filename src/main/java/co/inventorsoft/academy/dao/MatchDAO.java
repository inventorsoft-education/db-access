package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.dao.intetface.MatchDAOInterface;
import co.inventorsoft.academy.jdbc.MyJDBC;
import co.inventorsoft.academy.model.Match;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDAO implements MatchDAOInterface {
    private static final String INSERT_MATCH = "INSERT INTO matches (round, team1, team2, points_team1, points_team2) VALUES (?, ?, ?, ?, ?) RETURNING id;";
    private static final String SELECT_MATCH = "SELECT round,team1,team2,points_team1,points_team2 FROM matches;";
    private static final String SELECT_MATCH_BY_ID = "SELECT round,team1,team2,points_team1,points_team2 FROM matches WHERE id =?;";
    /**
     * This method add Match to database
     *
     * @param match input Match
     */
    @Override
    public int addMatch(Match match) {
        int result = -1;
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATCH)) {
            preparedStatement.setString(1, match.getRound());
            preparedStatement.setInt(2, match.getTeam1());
            preparedStatement.setInt(3, match.getTeam2());
            preparedStatement.setInt(4, match.getPointsTeam1());
            preparedStatement.setInt(5, match.getPointsTeam2());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                result = rs.getInt("id");
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return result;
    }

    /**
     * This method get Match from database by input index
     *
     * @param id input index
     * @return Match
     */
    @Override
    public Match getMatch(Integer id) {
       Match match = null;
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATCH_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String round = rs.getString("round");
                int team1 = rs.getInt("team1");
                int team2 = rs.getInt("team2");
                int score1 = rs.getInt("points_team1");
                int score2 = rs.getInt("points_team2");
                 match = new Match(round, team1, team2, score1, score2);
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return match;
    }

    /**
     * This method create list of matches from database
     */
    @Override
    public List<Match> getMatches() {
        List<Match> matches = new ArrayList<>();
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATCH)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String round = rs.getString("team");
                int team1 = rs.getInt("team1");
                int team2 = rs.getInt("team2");
                int score1 = rs.getInt("points_team1");
                int score2 = rs.getInt("points_team2");
                Match match = new Match(round, team1, team2, score1, score2);
                matches.add(match);
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return matches;
    }

    /**
     * This method delete Match from database
     *
     * @param id input Match id
     */
    @Override
    public void deleteMatch(Integer id) {

    }

    /**
     * This method update Match by id
     *
     * @param id    input id of Match
     * @param match new value of Match
     */
    @Override
    public void updateMatch(Integer id, Match match) {

    }
}

