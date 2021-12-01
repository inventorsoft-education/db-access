package com.example.demo.dao;

import com.example.demo.configuration.DBConfig;
import com.example.demo.model.Match;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDAO {
    public void save(List<Match> matches) {
        try (PreparedStatement preparedStatement =
                     DBConfig.getConnection().prepareStatement("INSERT INTO matches VALUES(?,?,?,?,?)")) {

            for (int i = 0; i < matches.size(); i++) {
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setString(2, matches.get(i).getFirstTeam());
                preparedStatement.setString(3, matches.get(i).getSecondTeam());
                preparedStatement.setString(4, matches.get(i).getRound());
                preparedStatement.setString(5, matches.get(i).getScore());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Match> getList() {
        List<Match> matches = null;
        try {
            PreparedStatement preparedStatement =
                    DBConfig.getConnection().prepareStatement("SELECT first_team, second_team, round, score FROM matches");

            ResultSet resultSet = preparedStatement.executeQuery();
            matches = new ArrayList<>();

            while (resultSet.next()) {
                matches.add(new Match(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
