package co.inventrosoft.springboottask.console;

import co.inventrosoft.springboottask.mapper.MatchMapper;

import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleParser.class);

    public List<Team> getTeams(BufferedReader reader) throws IOException {
        ArrayList<Team> teams = new ArrayList<>();
        int teamCount = getTeamCount(reader);
        for (int i = 0; i < teamCount; i++) {
            while (true) {
                Team team = getTeam(reader);
                // check if this team in list
                boolean isTeamNotInTeamList = teams.stream()
                        .map(Team::getName)
                        .noneMatch(team.getName()::equals);

                if (isTeamNotInTeamList) {
                    teams.add(team);
                    break;
                }
                LOGGER.error("Team with name \"" + team.getName() + "\" already exists");
            }
        }
        return teams;
    }

    public int getTeamCount(BufferedReader reader) throws IOException {
        int teamCount = 0;
        boolean valid = false;
        do {
            LOGGER.info("Enter number of teams: ");
            try {
                teamCount = Integer.parseInt(reader.readLine());

                // check if power of 2
                double log2 = Math.log(teamCount) / Math.log(2); // log base 2
                boolean isPowerOfTwo = (int) Math.ceil(log2) == (int) Math.floor(log2);

                if (teamCount < 2 || !isPowerOfTwo) {
                    LOGGER.error("Number of teams should be at least 4 and power of 2!");
                }
                else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Please, use only digits!");
            }
        }
        while (!valid);
        return teamCount;
    }

    /**
     * Parses data from console by format: {name} {capitan} {coach}.
     * Creates new {@link Team} object if team with this name does not exist.
     * @return created {@link Team} object
     */
    public Team getTeam(BufferedReader reader) throws IOException {
        String[] teamData;
        boolean valid = false;

        do {
            LOGGER.info("Enter a team by format (team fields can't have spaces!):\n{name} {capitan} {coach}");
            LOGGER.info("Every field should contain more than 2 letters");
            teamData = reader.readLine().split(" ");

            if (teamData.length == 3 && teamData[0].length() > 2 && teamData[1].length() > 2 && teamData[2].length() > 2) {
                valid = true;
            }
            else {
                LOGGER.error("Please, follow the format!");
            }
        }
        while (!valid);
        LOGGER.info("Team with name \"" + teamData[0] + "\" was added.\n");
        return new Team(teamData[0], teamData[1], teamData[2]);
    }

    /**
     * Parses result of match {@link MatchResult} from console by format: {team1 name} vs {team2 name} {score}.
     * Score format: {result of team1}:{result of team2}.
     * @return map, with keys: firstTeamName, secondTeamName, score
     */
    public MatchResult getResultOfMatch(BufferedReader reader) throws IOException {
        MatchResult matchResult;
        String scoreRegex = "^(\\d+):(\\d+)$";
        do {
            LOGGER.info("Enter a result of match by format:");
            LOGGER.info("{team1 name} vs {team2 name} {team1 result}:{team2 result}");
            String[] rawResult = reader.readLine().split(" ");

            if (rawResult.length != 4 || !rawResult[1].equals("vs") || !rawResult[3].matches(scoreRegex)) {
                LOGGER.error("Please, follow the format!");
                continue;
            }

            String firstTeam = rawResult[0];
            String secondTeam = rawResult[2];
            String[] score = rawResult[3].split(":");
            int firstTeamResult = Integer.parseInt(score[0]);
            int secondTeamResult = Integer.parseInt(score[1]);

            if (firstTeam.length() < 3 || secondTeam.length() < 3) {
                LOGGER.error("Team names should have more than 2 symbols!");
                continue;
            }
            if (firstTeam.equals(secondTeam)) {
                LOGGER.error("Team names should be different!");
                continue;
            }
            if (firstTeamResult == secondTeamResult) {
                LOGGER.error("There should be no draw!");
                continue;
            }
            matchResult = new MatchResult(firstTeam, secondTeam, firstTeamResult, secondTeamResult);
            break;
        }
        while (true);
        return matchResult;
    }

    public void printMatch(Match match) {
        LOGGER.info(match.toString());
    }

    public void printLine(String text) {
        LOGGER.info(text);
    }

    public void printWinner(Team team) {
        LOGGER.info("Winner:\n "+ team.toString());
    }
}
