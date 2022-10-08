package main;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import static main.DBDateStore.connection;

@Repository
public class RaceDAO {
    private static final String INSERT_RACE = "INSERT INTO races (rounds, first_team, second_team, time_of_race) VALUES (?,?,?,?);";

    @SneakyThrows
    public void addRace(Race race) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RACE)) {
            preparedStatement.setInt(1, race.getRound());
            preparedStatement.setInt(2, race.getFirstTeam().getId());
            preparedStatement.setInt(3, race.getSecondTeam().getId());
            preparedStatement.setString(4, race.getTime());
            preparedStatement.executeUpdate();
        }
    }
}
