package com.example.demo.repository;

import com.example.demo.entity.Team;
import com.example.demo.service.BDService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {
    private TeamsList teamsList;
    BDService BDService;

    public String getWinner(Team teamFirst, Team teamSecond, String round) {

        String result = round + ", " + teamFirst.getTeamName() + ",  " + teamSecond.getTeamName() + ", " + teamFirst.getPoints() + " : " + teamSecond.getPoints();
        if (teamFirst.getPoints() == teamSecond.getPoints()) {
            BDService.drawPoints(teamFirst);
            BDService.drawPoints(teamSecond);
            teamsList.deleteTeam(teamFirst);
        } else if (teamFirst.getPoints() > teamSecond.getPoints()) {
            BDService.pointForWinner(teamFirst);
            teamsList.deleteTeam(teamSecond);
        } else if (teamFirst.getPoints() < teamSecond.getPoints()) {
            BDService.pointForWinner(teamSecond);
            teamsList.deleteTeam(teamFirst);
        }
        return result;
    }
}

