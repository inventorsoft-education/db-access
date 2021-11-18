package com.example.task5.repository;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import com.example.task5.model.Tournament;
import com.example.task5.service.TournamentService;
import com.sun.tools.javac.Main;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DBDataStore implements DataStore {
    String DB_URL = "jdbc:hsqldb:mem:test";
    String USER = "SA";

    static String PREPARED_SQL = "SELECT * FROM TEAMS WHERE TEAMS.ID = ?";
    static String PREPARED_SQL_ALL = "SELECT * FROM TEAMS ";

    Connection connection;
    Statement statement;

    TournamentService tournamentService;

    @SneakyThrows
    DBDataStore(TournamentService tournamentService) {
        connection = DriverManager.getConnection(DB_URL, USER, StringUtils.EMPTY);
        statement = connection.createStatement();
        this.tournamentService = tournamentService;
    }

    @PostConstruct
    @SneakyThrows
    public void postConstructor() {
        String initSql = getSqlCode("init.sql");
        String insertSql = getSqlCode("insert.sql");

        statement.executeQuery(initSql);
        statement.executeQuery(insertSql);
        statement.close();
    }

    @SneakyThrows
    public List<Team> getTeams() {
        ArrayList teams;
        try (ResultSet resultSet = connection.prepareStatement(PREPARED_SQL_ALL).executeQuery()) {
            teams = createListTeams(resultSet);
        }
        return teams;
    }


    @SneakyThrows
    public List<Team> getTeam(Integer id) {
        PreparedStatement preparedStatement = connection.prepareStatement(PREPARED_SQL);
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.close();
            connection.close();
            return createListTeams(resultSet);
        }
    }

    @SneakyThrows
    public List<Tournament> getTournaments() {
        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        PREPARED_SQL_ALL
                                .replace("TEAMS", "TOURNAMENT"));

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.close();
            return createListTournament(resultSet);
        }
    }

    @SneakyThrows
    public List<Object> getTournament(Integer id) {
        List<Object> objects = new ArrayList<>();

        PreparedStatement preparedStatement0 = connection
                .prepareStatement(
                        "SELECT * FROM TOURNAMENT WHERE ID = " + id);
        try (ResultSet resultSet = preparedStatement0.executeQuery()) {
            objects.add(createListTournament(resultSet));
        }

        String query = """
                SELECT
                t1.ID,
                t1.NAME,
                t1.CAPTAIN,
                t1.COACH,
                t2.ID,
                t2.NAME,
                t2.CAPTAIN,
                t2.COACH,
                GAMES.ROUND,
                GAMES.RESULT
                FROM GAMES
                LEFT JOIN TEAMS t1 ON GAMES.FIRST_TEAM = t1.ID
                LEFT JOIN TEAMS t2 ON GAMES.SECOND_TEAM = t2.ID 
                WHERE GAMES.TOURNAMENT = ?
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        List<Game> games = new ArrayList<>();
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Team team1 = new Team(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                team1.setId(rs.getInt(1));

                Team team2 = new Team(
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                team2.setId(rs.getInt(5));
                games.add(new Game(
                        team1,
                        team2,
                        rs.getString(9),
                        rs.getString(10)));
            }
        }
        objects.add(games);
        return objects;
    }

    @SneakyThrows
    @Override
    public List<Object> createTournament(String name, List<Team> teams) {
        int j = insertTournament(name);

        insertTeam(teams);

        insertGame(teams, j);

        return getTournament(j);

    }

    @SneakyThrows
    private int insertTournament(String name){
        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        "INSERT INTO TOURNAMENT (NAME) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();

        int j;
        try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            resultSet.next();
            j = resultSet.getInt(1);
        }
        preparedStatement.close();
        return j;
    }

    @SneakyThrows
    private void insertTeam(List<Team> teams){
        PreparedStatement preparedStatement1 = connection
                .prepareStatement(
                        "INSERT INTO TEAMS (NAME, CAPTAIN, COACH) VALUES(?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
        tournamentService.register(teams);
        for (Team t : teams) {
            preparedStatement1.setString(1, t.getName());
            preparedStatement1.setString(2, t.getCaptain());
            preparedStatement1.setString(3, t.getCoach());
            preparedStatement1.executeUpdate();
            try (ResultSet resultSet1 = preparedStatement1.getGeneratedKeys()) {
                resultSet1.next();
                t.setId(resultSet1.getInt(1));
            }
        }
        preparedStatement1.close();
    }

    @SneakyThrows
    private void insertGame(List<Team> teams, int j){
        List<Game> games = tournamentService.start(teams);
        PreparedStatement preparedStatement3 = connection
                .prepareStatement(
                        "INSERT INTO GAMES " +
                                "(FIRST_TEAM, SECOND_TEAM, ROUND, RESULT, TOURNAMENT) " +
                                "VALUES (?, ?, ?, ?, ?)");
        for (Game game : games) {
            preparedStatement3.setInt(1, game.getTeamFirst().getId());
            preparedStatement3.setInt(2, game.getTeamSecond().getId());
            preparedStatement3.setString(3, game.getRound());
            preparedStatement3.setString(4, game.getResult());
            preparedStatement3.setInt(5, j);
            preparedStatement3.executeUpdate();

        }
    }

    @SneakyThrows
    private ArrayList<Team> createListTeams(ResultSet resultSet) {
        String name;
        String captain;
        String coach;
        Integer Id;
        ArrayList<Team> teams = new ArrayList<>();
        while (resultSet.next()) {
            Id = resultSet.getInt("ID");
            name = resultSet.getString("NAME");
            captain = resultSet.getString("CAPTAIN");
            coach = resultSet.getString("COACH");
            Team team = new Team(name, captain, coach);
            team.setId(Id);
            teams.add(team);
        }
        return teams;
    }

    @SneakyThrows
    private ArrayList<Tournament> createListTournament(ResultSet resultSet) {
        String name;
        Integer id;
        ArrayList<Tournament> tournaments = new ArrayList<>();
        while (resultSet.next()) {
            name = resultSet.getString("NAME");
            id = resultSet.getInt("ID");
            Tournament tournament = new Tournament(name);
            tournament.setId(id);
            tournaments.add(tournament);
        }
        return tournaments;
    }

    @PreDestroy
    @SneakyThrows
    public void preDestroy() {
        connection.close();
        statement.close();
    }

    @SneakyThrows
    private static String getSqlCode(@NonNull String filename) {
        URL sqlResource = Main.class.getClassLoader().getResource(filename);
        File file = new File(sqlResource.getPath());
        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }

}

