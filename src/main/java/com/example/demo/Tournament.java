package com.example.demo;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.service.MatchService;
import com.example.demo.service.TeamService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournament {

    TeamService teamService;
    MatchService matchService;
    Team winner;

    Tournament(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }

    public void generateMatches() {
        List<Team> teams = teamService.teamsToList();
        List<Match> matches = new ArrayList<>();
        int teamListSize = teams.size();
        int round = teamListSize / 2;
        if(isPowerOfTwo(teamListSize)) {
            int roundCount = countMatches(teamListSize);
            for (int i = 0; i < roundCount; i++) {
                int teamCount = 0;
                for (int j = 0; j < round; j++) {
                    Match currentMatch = new Match(round, teams.get(teamCount), teams.get(teamCount + 1));
                    teamCount++;
                    Team winner = currentMatch.getWinner();
                    String score = currentMatch.getScore();
                    matches.add(currentMatch);
                    teams.remove(winner);
                }
                round /= 2;
            }
        }
        winner = teams.get(0);
        matchService.saveMatches(matches);
    }

    int countMatches(int teamListSize) {
        int count = 0;
        while (teamListSize > 1) {
            teamListSize /= 2;
            count++;
        }
        return count;
    }

    public boolean isPowerOfTwo(int number) {
        return (int)(Math.ceil((Math.log(number) / Math.log(2))))
                == (int)(Math.floor(((Math.log(number) / Math.log(2)))));
    }

}
