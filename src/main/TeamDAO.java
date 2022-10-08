package main;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import static main.DBDateStore.connection;

@Repository
public class TeamDAO {
    private static final String SELECT_TEAMS = "SELECT id, team_name, horse, rider FROM teams;";

    @SneakyThrows
    public LinkedList<Team> getTeams() {
        LinkedList<Team> teams = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String teamName = resultSet.getString("team_name");
                String horse = resultSet.getString("horse");
                String rider = resultSet.getString("rider");
                teams.add(new Team(id, teamName, horse, rider));
            }
        }
        return teams;
    }
}
