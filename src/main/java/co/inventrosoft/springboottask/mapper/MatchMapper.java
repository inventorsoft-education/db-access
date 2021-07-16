package co.inventrosoft.springboottask.mapper;

import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchMapper {
    public static Match fromResultSetToMatch(ResultSet resultSet) throws SQLException {
        Match match = new Match();

        match.setId(resultSet.getInt("match_id"));
        match.setTournamentId(resultSet.getInt("tournament_id"));

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
}
