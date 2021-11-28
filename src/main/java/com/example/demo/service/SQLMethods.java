package com.example.demo.service;

import com.example.demo.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.*;

@Service
@AllArgsConstructor
public class SQLMethods {
    private static final String URL = "jdbc:mysql://localhost:3306/tournament_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    public SQLMethods teamRegister(String teamName, String coach, String captain) {
        Integer points = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("INSERT into teams(team_name, coach, captain,points) values(?,?,?,?)");
            statement.setNString(1, teamName);
            statement.setNString(2, coach);
            statement.setNString(3, captain);
            statement.setInt(4, points);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public SQLMethods pointForWinner(Team team) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("update teams set points = (points+3) WHERE team_name=?");
            statement.setNString(1, team.getTeamName());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public SQLMethods drawPoints(Team team) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("update teams set points = (points+1) WHERE team_name=?");
            statement.setNString(1, team.getTeamName());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public SQLMethods showTournamentTable() {
        Integer number = 0;
        String currentQuery = "SELECT team_name,points FROM teams ORDER BY points DESC ;";

        try {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(currentQuery);
                System.out.println("Place | Team | Points");
                while (resultSet.next()) {
                    number++;
                    System.out.println(number + ".     " + resultSet.getString("team_name") + "-" + resultSet.getString("points"));
                }
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public SQLMethods delete() {
        String currentQuery = "TRUNCATE teams";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(currentQuery);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
