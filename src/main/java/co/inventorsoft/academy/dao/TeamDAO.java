package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.dao.intetface.TeamDAOInterface;
import co.inventorsoft.academy.jdbc.MyJDBC;
import co.inventorsoft.academy.model.Team;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.inventorsoft.academy.jdbc.MyJDBC.connection;

@Repository
public class TeamDAO implements TeamDAOInterface {
    private static final String INSERT_TEAM = "INSERT INTO teams (team, pilot1, pilot2) VALUES (?, ?, ?);";
    private static final String COUNT_OF_TEAMS = "SELECT COUNT(id) as count FROM teams;";
    private static final String SELECT_TEAM_BY_ID = "SELECT team, pilot1, pilot2 FROM teams WHERE id = ?;";
    private static final String SELECT_ID = "SELECT id FROM teams WHERE team = ? AND pilot1= ? AND pilot2= ?;";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE id = ?;";
    private static final String SELECT_TEAMS = "SELECT team, pilot1, pilot2 FROM teams;";
    private static final String UPDATE_TEAM = "UPDATE teams SET team = ?, pilot1= ?, pilot2 = ? where id = ?;";

    /**
     * This method add {@link Team} to database
     *
     * @param team input {@link Team}
     */
    @Override
    public void addTeam(Team team) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEAM)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getPilot1());
            preparedStatement.setString(3, team.getPilot2());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }

    /**
     * This method return count of {@link Team} in database
     *
     * @return count of teams or -1 if we have error
     */
    @Override
    public int size() {
        int result = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_OF_TEAMS); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return result;
    }

    /**
     * This method get {@link Team} from database by input index
     *
     * @param id input index
     * @return {@link Team}
     */
    @Override
    public Team getTeam(Integer id) {
        Team team = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAM_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("team");
                String pilot1 = rs.getString("pilot1");
                String pilot2 = rs.getString("pilot2");
                team = new Team(name, pilot1, pilot2);
            }
            rs.close();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return team;
    }

    /**
     * This method find id in database from input {@link Team}
     *
     * @param team input {@link Team}
     * @return id of {@link Team} or -1 if we have error
     */
    @Override
    public int getId(Team team) {
        int result = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getPilot1());
            preparedStatement.setString(3, team.getPilot2());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return result;
    }

    /**
     * This method create list of {@link Team} from database
     */
    @Override
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAMS); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("team");
                String pilot1 = rs.getString("pilot1");
                String pilot2 = rs.getString("pilot2");
                teams.add(new Team(name, pilot1, pilot2));
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return teams;
    }

    /**
     * This method delete {@link Team} from database
     *
     * @param id input {@link Team} id
     */
    @Override
    public void deleteTeam(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TEAM)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }

    /**
     * This method update {@link Team} by id
     *
     * @param id   input id of {@link Team}
     * @param team new value of {@link Team}
     */
    @Override
    public void updateTeam(Integer id, Team team) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TEAM)) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getPilot1());
            statement.setString(3, team.getPilot2());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }
}

