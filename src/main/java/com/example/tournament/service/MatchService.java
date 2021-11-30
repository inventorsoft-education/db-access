package com.example.tournament.service;

import com.example.tournament.model.MatchDto;
import com.example.tournament.model.Team;
import com.example.tournament.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MatchService extends GeneralService<MatchDto, Integer>{

    private MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository){
        super(matchRepository);
        this.matchRepository = matchRepository;
    }

    public Team getResult(Team teamOne, Team teamTwo, String round) throws SQLException {
        Map<Team, Integer> result = new HashMap<>();

        int teamOneScore = (int) (Math.random() * 5);
        int teamTwoScore = (int) (Math.random() * 5);

        if(teamOneScore == teamTwoScore){
            teamOneScore++;
        }

        result.put(teamOne, teamOneScore);
        result.put(teamTwo, teamTwoScore);

        String score = teamOneScore + ":" + teamTwoScore;

        matchRepository.save(new MatchDto(round, teamOne.getName(), teamTwo.getName(), score));

        if(result.get(teamOne) > result.get(teamTwo)){
            return teamOne;
        }
        else{
            return teamTwo;
        }
    }


}
