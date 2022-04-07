package mapper;

import entity.Match;
import entity.Team;
import entity.Tournament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper {
    public static List<Tournament> tournamentList(ResultSet resultSet) throws SQLException {
        List<Tournament> result = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int rounds = resultSet.getInt("ROUNDS");
            result.add(new Tournament(id, rounds));
        }
        return result;
    }

    public static List<Match> matchList(ResultSet resultSet) throws SQLException {
        List<Match> result = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int tournamentId = resultSet.getInt("TOURNAMENTID");
            int firstTeamId = resultSet.getInt("FIRSTTEAMID");
            int secondTeamId = resultSet.getInt("SECONDTEAMID");
            int round = resultSet.getInt("ROUND");
            String matchResult = resultSet.getString("MATCHRESULT");
            result.add(new Match(id, tournamentId, firstTeamId, secondTeamId, round, matchResult));
        }
        return result;
    }

    public static List<Team> teamList(ResultSet resultSet) throws SQLException {
        List<Team> result = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String coach = resultSet.getString("COACH");
            String name = resultSet.getString("TEAMNAME");
            String captain = resultSet.getString("CAPTAIN");
            result.add(new Team(id, name, captain, coach));
        }
        return result;
    }
}
