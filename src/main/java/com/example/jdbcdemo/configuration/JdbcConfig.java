package com.example.jdbcdemo.configuration;


import com.example.jdbcdemo.model.Match;
import com.example.jdbcdemo.model.Team;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JdbcConfig {
    static String URL = "jdbc:postgresql://localhost:5432/tournament";
    static String USERNAME = "postgres";
    static String PASSWORD = "postgres";

    static Connection connection;

    public void saveTeam(List<Team> teams) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEAMS VALUES(?, ?, ?, ?)");
            for (int i = 0; i < teams.size(); i++) {
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setString(2, teams.get(i).getName());
                preparedStatement.setString(3, teams.get(i).getCapitan());
                preparedStatement.setString(4, teams.get(i).getCoach());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveTeam(Team team) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEAMS VALUES(?, ?, ?, ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, team.getName());
            preparedStatement.setString(3, team.getCapitan());
            preparedStatement.setString(4, team.getCoach());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeTeam(Team team) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM teams WHERE id = ?");
            preparedStatement.setInt(1, getTeamIdByName(team.getName()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Team> teamsToList() {
        List<Team> teams = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, captain, coach FROM teams");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teams.add(new Team(resultSet.getString(1),
                                   resultSet.getString(2),
                                   resultSet.getString(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teams;
    }

    public static void saveMatches(List<Match> matches) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MATCHES VALUES(?, ?, ?, ?, ?)");
            for (int i = 0; i < matches.size(); i++) {
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setInt(2, matches.get(i).getRound());
                preparedStatement.setInt(3, getTeamIdByName(matches.get(i).getTeam1().getName()));
                preparedStatement.setInt(4, getTeamIdByName(matches.get(i).getTeam2().getName()));
                preparedStatement.setString(5, matches.get(i).getScore());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Match> matchesToList() {
        List<Match> matches = new ArrayList<>();
        Match match = new Match();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT round, team1, team2, score FROM matches");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                match.setRound(resultSet.getInt(1));
                match.setTeam1(getTeamById(resultSet.getInt(2)));
                match.setTeam1(getTeamById(resultSet.getInt(3)));
                match.setScore(resultSet.getString(4));
                matches.add(match);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matches;
    }

    public static Team getTeamById(int id) {
        Team team = new Team();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, captain, coach FROM teams WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            team.setName(resultSet.getString(1));
            team.setCapitan(resultSet.getString(2));
            team.setCoach(resultSet.getString(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return team;
    }

    public static int getTeamIdByName(String name) {
        int id = 0;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM teams WHERE name = ? LIMIT 1");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static void deleteMatches() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE matches");
            preparedStatement.executeQuery();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
