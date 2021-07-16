package co.inventrosoft.hibernate_task.mapper;

import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchMapper {
    public static Match fromResultSetToMatch(ResultSet resultSet) throws SQLException {
        Match match = new Match();

        match.setId(resultSet.getInt("match_id"));
        //match.setTournamentId(resultSet.getInt("tournament_id"));

        int firstTeamId = resultSet.getInt("t1_id");
        if (firstTeamId != 0) {
            match.setFirstTeam(
                    new Team(firstTeamId, resultSet.getString("t1_name"),
                            resultSet.getString("t1_capitan"), resultSet.getString("t1_coach"))
            );
        }
        int secondTeamId = resultSet.getInt("t2_id");
        if (secondTeamId != 0) {
            match.setSecondTeam(
                    new Team(secondTeamId, resultSet.getString("t2_name"),
                            resultSet.getString("t2_capitan"), resultSet.getString("t2_coach"))
            );
        }
        match.setFirstTeamResult(resultSet.getInt("first_team_result"));
        match.setSecondTeamResult(resultSet.getInt("second_team_result"));
        match.setPlayed(resultSet.getBoolean("is_played"));
        match.setRoundCode(resultSet.getInt("round_code"));
        match.setOrder(resultSet.getInt("match_order"));

        return match;
    }

    public static String fromObjectToString(Match match) {
        String str = "\n";
        str += match.getRoundCode() != 1 ? "Round 1/" + match.getRoundCode() : "Final!";

        if (match.getPlayed()) {
            str += "\nScore: " + match.getScore();
        }
        str += "\nTeam 1: " + TeamMapper.fromObjectToString(match.getFirstTeam()) + "\n";
        str += "Team 2: " + TeamMapper.fromObjectToString(match.getSecondTeam());

        return str;
    }
}
