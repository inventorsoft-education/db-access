package com.example.demo.tournament;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.dao.MatchDAO;
import com.example.demo.dao.TeamDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Tournament {
    MatchDAO matchDAO;
    TeamDAO teamDAO;

    public void createAndSaveMatchesBasedOnListOfTeams(List<Team> teams) {
        List<Match> matches = new ArrayList<>();

        if (teams.size() % 2 == 0) {
            Team firstTmpTeam, secondTmpTeam;
            Integer firstRandomValue, secondRandomValue, currentRound;
            int countMatchesAtTheRound = teams.size() / 2;
            int cntOfMatches = teams.size() - 1;
            int cntOfRounds = returnCntOfRounds(cntOfMatches);
            Set<Integer> indexes = generateIndexSet(teams.size());
            Set<Integer> tmpIndexes;

            for (int i = 1; i <= cntOfRounds; i++) {
                currentRound = i;
                tmpIndexes = new HashSet<>();
                for (int j = 0; j < countMatchesAtTheRound; j++) {
                    firstRandomValue = getValueFromSet(indexes);
                    indexes.remove(firstRandomValue);
                    secondRandomValue = getValueFromSet(indexes);
                    indexes.remove(secondRandomValue);

                    firstTmpTeam = teams.get(firstRandomValue);
                    secondTmpTeam = teams.get(secondRandomValue);

                    Match match = new Match(firstTmpTeam.getName(), secondTmpTeam.getName(), currentRound, cntOfRounds);
                    matches.add(match);

                    if ((getWinner(match).getName().equals(firstTmpTeam.getName()))) {
                        tmpIndexes.add(firstRandomValue);
                    } else {
                        tmpIndexes.add(secondRandomValue);
                    }
                }
                indexes = new HashSet<>(tmpIndexes);
                countMatchesAtTheRound /= 2;
            }
        }
        matchDAO.save(matches);
    }

    private Integer getValueFromSet(Set<Integer> indexes) {
        Integer tempIndex = null;
        for(Integer index: indexes) {
            tempIndex = index;
            break;
        }
        return tempIndex;
    }

    private Integer returnCntOfRounds(int cntOfMatches) {
        int tmp = cntOfMatches + 1;
        Integer i = 0;
        while (tmp > 1) {
            tmp = tmp / 2;
            i++;
        }
        return i;
    }

    private Set<Integer> generateIndexSet(int indexListSize) {
        Set<Integer> setOfIndexes = new HashSet<>();
        for (int i = 0; i < indexListSize; i++) {
            setOfIndexes.add(i);
        }
        return setOfIndexes;
    }

    public Team getWinner() {
        List<Match> matches = matchDAO.getList();
        return teamDAO.getTeamByName(matches.get(matches.size() - 1).getWinnerName());
    }

    public Team getWinner(Match match) {
        return teamDAO.getTeamByName(match.getWinnerName());
    }

    public void saveAllTeams(List<Team> teamList) {
        teamDAO.save(teamList);
    }

    public List<Team> getAllTeams() {
        return teamDAO.getList();
    }

    public List<Match> getAllMatches() {
        return matchDAO.getList();
    }

}
