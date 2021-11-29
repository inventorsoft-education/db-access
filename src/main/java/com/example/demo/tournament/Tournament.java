package com.example.demo.tournament;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.dao.MatchDAO;
import com.example.demo.dao.TeamDAO;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Value
@AllArgsConstructor
public class Tournament {

    public boolean createAndSaveMatchesBasedOnListOfTeams(List<Team> teams) {
        List<Match> matches = new ArrayList<>();

        if (teams.size() % 2 == 0) {
            Team firstTmpTeam;
            Team secondTmpTeam;
            int firstRandomValue;
            int secondRandomValue;
            int countMatchesAtTheRound = teams.size() / 2;
            int cntOfMatches = teams.size() - 1;
            int currentRound;
            int cntOfRounds;
            List<Integer> indexes = generateIndexList(teams.size());
            List<Integer> tmpIndexes;
            cntOfRounds = returnCntOfRounds(cntOfMatches);

            for (int i = 1; i <= cntOfRounds; i++) {
                currentRound = i;
                tmpIndexes = new ArrayList<>();
                for (int j = 0; j < countMatchesAtTheRound; j++) {
                    firstRandomValue = getRandomValueFromArray(indexes);
                    indexes.remove((Object) firstRandomValue);
                    secondRandomValue = getRandomValueFromArray(indexes);
                    indexes.remove((Object) secondRandomValue);

                    firstTmpTeam = teams.get(firstRandomValue);
                    secondTmpTeam = teams.get(secondRandomValue);

                    Match match = new Match(firstTmpTeam.getName(), secondTmpTeam.getName(), currentRound, cntOfRounds, Match.generateScore());
                    matches.add(match);

                    if (getWinner(match).getName().equals(firstTmpTeam.getName())) {
                        tmpIndexes.add(firstRandomValue);
                    }
                    else tmpIndexes.add(secondRandomValue);
                }
                indexes = new ArrayList<>(tmpIndexes);
                countMatchesAtTheRound /= 2;
            }
        }
        MatchDAO.save(matches);
        return true;
    }

    private Integer getRandomValueFromArray(List<Integer> indexes) {
        if(indexes.size() == 1) return indexes.get(indexes.size()-1);

        Random random = new Random();
        Integer tmp = random.nextInt(indexes.get(indexes.size()-1));
        while (!indexes.contains(tmp)) {
            tmp = random.nextInt(indexes.get(indexes.size()-1));
        }
        return tmp;
    }

    private Integer returnCntOfRounds(int cntOfMatches) {
        Integer tmp = cntOfMatches + 1;
        Integer i = 0;
        while (tmp > 1) {
            tmp = tmp / 2;
            i++;
        }
        return i;
    }

    private List<Integer> generateIndexList(int indexListSize) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < indexListSize; i++) {
            arr.add(i);
        }
        return arr;
    }

    public Team getWinner() {
        List<Match> matches = MatchDAO.getList();
        Team winner = TeamDAO.getTeamByName(matches.get(matches.size() - 1).getWinnerName());
        return winner;
    }

    public Team getWinner(Match match) {
        Team winner = TeamDAO.getTeamByName(match.getWinnerName());
        return winner;
    }
}
