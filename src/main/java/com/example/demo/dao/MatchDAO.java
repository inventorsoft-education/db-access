package com.example.demo.dao;

import com.example.demo.configuration.DBConfig;
import com.example.demo.model.Match;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchDAO {
    static int id;

    static {
        id = 1;
    }

    public static void save(List<Match> matches) {
        try {
            PreparedStatement preparedStatement =
                    DBConfig.getConnection().prepareStatement("insert into matches values(?,?,?,?,?)");

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

    public static List<Match> getList() {
        List<Match> matches = null;
        try {
            PreparedStatement preparedStatement =
                    DBConfig.getConnection().prepareStatement("select * from matches");

            ResultSet resultSet = preparedStatement.executeQuery();
            matches = new ArrayList<>();

            while (resultSet.next()) {
                matches.add(new Match(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
