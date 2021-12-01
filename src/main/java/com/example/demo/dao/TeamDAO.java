package com.example.demo.dao;

import com.example.demo.configuration.DBConfig;
import com.example.demo.model.Team;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamDAO {
    public void save(List<Team> teams) {
        try {
            PreparedStatement preparedStatement =
                    DBConfig.getConnection().prepareStatement("INSERT INTO teams VALUES(?,?,?,?)");

            for (int i = 0; i < teams.size(); i++) {
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setString(2, teams.get(i).getName());
                preparedStatement.setString(3, teams.get(i).getCaptain());
                preparedStatement.setString(4, teams.get(i).getCoach());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Team> getList() {
        List<Team> teams = null;

        try {
            PreparedStatement preparedStatement =
                    DBConfig.getConnection().prepareStatement("SELECT * FROM teams");

            ResultSet resultSet = preparedStatement.executeQuery();
            teams = new ArrayList<>();

            while (resultSet.next()) {
                teams.add(new Team(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public Team getTeamByName(String name) {
        Team team = null;
        try {
            PreparedStatement preparedStatement =
                    DBConfig.getConnection().prepareStatement(
                            "SELECT teams.name, teams.captain, teams.coach " +
                                    "FROM teams WHERE name = ? FETCH FIRST ROW ONLY");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team = new Team(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return team;
    }
}