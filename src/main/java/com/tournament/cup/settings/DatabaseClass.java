package com.tournament.cup.settings;

import com.tournament.cup.play.Squad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@AllArgsConstructor
public class DatabaseClass {
    private static final String URL = "jdbc:mysql://localhost:3306/cup";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    public void teamAuthorization(String teamName, String coach, String captain) {
        Integer points = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement state = connection.prepareStatement("INSERT INTO teams(team_name, captain, coach,points) VALUES (?,?,?,?)");
            state.setNString(1, teamName);
            state.setNString(2, captain);
            state.setNString(3, coach);
            state.setInt(4, points);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTable() {
        Integer number = 0;
        String query = "SELECT team_name,points FROM teams ORDER BY points DESC ;";

        try {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                Statement state = connection.createStatement();
                ResultSet resultSet = state.executeQuery(query);
                System.out.println("Place | Team | Points");
                while (resultSet.next()) {
                    number++;
                    System.out.println(number + ".     " + resultSet.getString("team_name") + "-" + resultSet.getString("points"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void winnerPoints(Squad squad) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement state = connection.prepareStatement("UPDATE teams set points = (points+3) WHERE team_name=?");
            state.setNString(1, squad.getTeamName());
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void drawPoints(Squad squad) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement state = connection.prepareStatement("UPDATE teams SET points = (points+1) WHERE team_name=?");
            state.setNString(1, squad.getTeamName());
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete() {
        String currentQuery = "TRUNCATE teams";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement state = connection.prepareStatement(currentQuery);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
