package com.example.demo.repository;

import com.example.demo.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {
    private TeamsList teamsList;
    SQLMethods sqlMethods;

    public String getWinner(Team teamFirst, Team teamSecond, String round) {

        String result = round + ", " + teamFirst.getTeamName() + ",  " + teamSecond.getTeamName() + ", " + teamFirst.getPoints() + " : " + teamSecond.getPoints();
        if (teamFirst.getPoints() == teamSecond.getPoints()) {
            sqlMethods.drawPoints(teamFirst);
            sqlMethods.drawPoints(teamSecond);
            teamsList.deleteTeam(teamFirst);
        } else if (teamFirst.getPoints() > teamSecond.getPoints()) {
            sqlMethods.pointForWinner(teamFirst);
            teamsList.deleteTeam(teamSecond);
        } else if (teamFirst.getPoints() < teamSecond.getPoints()) {
            sqlMethods.pointForWinner(teamSecond);
            teamsList.deleteTeam(teamFirst);
        }
        return result;
    }
}

