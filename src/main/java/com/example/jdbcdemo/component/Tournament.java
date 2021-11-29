package com.example.jdbcdemo.component;

import com.example.jdbcdemo.configuration.JdbcConfig;
import com.example.jdbcdemo.model.Match;
import com.example.jdbcdemo.model.Team;
import com.example.jdbcdemo.repository.MatchList;
import com.example.jdbcdemo.repository.TeamList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournament {

    TeamList teamList;
    MatchList matchList;
    Team winner;

    Tournament(TeamList teamList, MatchList matchList) {
        this.teamList = teamList;
        this.matchList = matchList;
    }

    public void generateMatches() {
        int teamListSize = teamList.getSize();
        int round = teamListSize / 2;
        if(isPowerOfTwo(teamListSize)) {
            int roundCount = countMatches(teamListSize);
            for (int i = 0; i < roundCount; i++) {
                int teamCount = 0;
                for (int j = 0; j < round; j++) {
                    Match currentMatch = new Match(round, teamList.getTeamList().get(teamCount), teamList.getTeamList().get(teamCount + 1));
                    teamCount++;
                    Team winner = currentMatch.getWinner();
                    String score = currentMatch.getScore();
                    matchList.getMatchList().add(currentMatch);
                    teamList.getTeamList().remove(winner);
                }
                round /= 2;
            }
        }
        winner = teamList.getTeamList().get(0);
        JdbcConfig.saveMatches(matchList.getMatchList());
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

    public Team getWinner() {
        return winner;
    }
}
