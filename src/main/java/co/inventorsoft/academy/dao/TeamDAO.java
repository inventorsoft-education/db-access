package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.dao.intetface.TeamDAOInterface;
import co.inventorsoft.academy.jdbc.MyJDBC;
import co.inventorsoft.academy.model.Team;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamDAO implements TeamDAOInterface {
    private static final String INSERT_TEAM = "INSERT INTO teams (team, pilot1, pilot2) VALUES (?, ?, ?);";
    private static final String COUNT_OF_TEAMS = "SELECT COUNT(id) as count FROM teams;";
    private static final String SELECT_TEAM_BY_ID = "SELECT teams.team,teams.pilot1,teams.pilot2 FROM teams WHERE id =?;";
    private static final String SELECT_ID = "SELECT teams.id FROM teams WHERE team =? AND pilot1=? AND pilot2=?;";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE id = ?;";
    private static final String SELECT_TEAMS = "SELECT teams.team,teams.pilot1,teams.pilot2 FROM teams;";
    private static final String UPDATE_TEAM = "UPDATE teams SET team = ?, pilot1= ?, pilot2 =? where id = ?;";

    /**
     * This method add Team to database
     *
     * @param team input Team
     */
    @Override
    public void addTeam(Team team) {
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEAM)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getPilot1());
            preparedStatement.setString(3, team.getPilot2());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }

    /**
     * This method return count of teams in database
     *
     * @return count od teams
     */
    @Override
    public int size() {
        int result = -1;
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_OF_TEAMS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return result;
    }

    /**
     * This method get Team from database by input index
     *
     * @param id input index
     * @return Team
     */
    @Override
    public Team getTeam(Integer id) {
        Team team = null;
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAM_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("team");
                String pilot1 = rs.getString("pilot1");
                String pilot2 = rs.getString("pilot2");
                team = new Team(name, pilot1, pilot2);
            }
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
        return team;
    }

    /**
     * This method find id in database from input Team
     *
     * @param team input Team
     * @return id of Team
     */
    @Override
    public int getId(Team team) {
        int result = -1;
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getPilot1());
            preparedStatement.setString(3, team.getPilot2());
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
     * This method create list of teams from database
     */
    @Override
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAMS)) {
            ResultSet rs = preparedStatement.executeQuery();
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
     * This method delete Team from database
     *
     * @param id input Team id
     */
    @Override
    public void deleteTeam(Integer id) {
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TEAM);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            MyJDBC.printSQLException(e);
        }
    }

    /**
     * This method update Team by id
     *
     * @param id   input id of Team
     * @param team new value of Team
     */
    @Override
    public void updateTeam(Integer id, Team team) {
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TEAM);) {
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

