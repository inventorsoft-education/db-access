package sql.src.main.java.additional;

import model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataOperations {

    private static Statement statement;

    public static void clearTable() throws SQLException{
        statement = DatabaseConnector.getStatement();
        statement.execute("Truncate table results");
    }

    public static List<Team> getTeams() throws Exception{
        List<Team> teamList = new ArrayList<>();
        statement = DatabaseConnector.getStatement();
        ResultSet rs = statement.executeQuery("SELECT name, coach_id, captain_id FROM teams");

        while(rs.next()){
            String name = rs.getString("name");
            String coach = rs.getString("coach_id");
            String captain = rs.getString("captain_id");
            teamList.add(new Team(name, coach, captain));
        }

        return teamList;
    }

    public static void writeResult(String round, String teamOne, String teamTwo, String score) throws SQLException {
        statement = DatabaseConnector.getStatement();
        statement.executeUpdate("INSERT INTO results(round, team_one_name, team_two_name, score) " +
                "values( '" + round + "' , '" + teamOne + "', '" + teamTwo + "' , '" + score +"');");
    }

    public static void writeWinner(String team) throws SQLException{
        statement = DatabaseConnector.getStatement();
        statement.executeUpdate("INSERT INTO tournament_winners(team_name) " +
                "values( '" + team + "');");
    }
}
