package com.example.demo.tournament;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.service.MatchService;
import com.example.demo.service.TeamService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Tournament {
    MatchService matchService;
    TeamService teamService;

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

                    Match match = new Match();
                    match.setFirstTeam(firstTmpTeam);
                    match.setSecondTeam(secondTmpTeam);
                    match.setRound(currentRound, cntOfRounds);
                    match.setScore(matchService.generateScore());
                    matches.add(match);

                    if (getWinner(match).getName().equals(firstTmpTeam.getName())) {
                        tmpIndexes.add(firstRandomValue);
                    } else tmpIndexes.add(secondRandomValue);
                }
                indexes = new ArrayList<>(tmpIndexes);
                countMatchesAtTheRound /= 2;
            }
        }
        matchService.saveAll(matches);
        return true;
    }

    private Integer getRandomValueFromArray(List<Integer> indexes) {
        if (indexes.size() <= 2) return indexes.get(indexes.size() - 1);

        Random random = new Random();
        Integer tmp = random.nextInt(indexes.get(indexes.size() - 1));
        while (!indexes.contains(tmp)) {
            tmp = random.nextInt(indexes.get(indexes.size() - 1));
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
@Transactional(readOnly = true)
    public Team getWinner() {
        List<Match> matches = matchService.getAll();
        Team teamWinner = matchService.getWinner(matches.get(matches.size() - 1));
        Hibernate.initialize(teamWinner);
        return teamWinner;
    }

    public Team getWinner(Match match) {
        return matchService.getWinner(match);
    }

    public List<Team> getTeamList() {
        return teamService.getAll();
    }

    public List<Match> getMatchList() {
        return matchService.getAll();
    }

    public void saveAllTeams(List<Team> teamList) {
        teamService.saveAll(teamList);
    }
}
