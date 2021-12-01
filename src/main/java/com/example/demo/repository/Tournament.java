package com.example.demo.repository;

import com.example.demo.entity.Team;
import com.example.demo.service.DB_Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {
    private TeamsList teamsList;
    DB_Service dbService;

    public String getWinner(Team teamFirst, Team teamSecond, String round) {

        String result = round + ", " + teamFirst.getTeamName() + ",  " + teamSecond.getTeamName() + ", " + teamFirst.getGoals() + " : " + teamSecond.getGoals();
        if (teamFirst.getGoals() == teamSecond.getGoals()) {
            teamsList.deleteTeam(teamFirst);
            dbService.drawPoints(teamFirst, teamSecond);
        } else if (teamFirst.getGoals() > teamSecond.getGoals()) {
            teamsList.deleteTeam(teamSecond);
            dbService.winnerPoints(teamFirst);
        } else if (teamFirst.getGoals() < teamSecond.getGoals()) {
            teamsList.deleteTeam(teamFirst);
            dbService.winnerPoints(teamSecond);
        }
        return result;
    }
}

