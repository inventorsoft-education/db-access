package com.tournament.cup.play;

import com.tournament.cup.settings.DatabaseClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {
    DatabaseClass databaseClass;
    private ListOfTeams listOfTeams;

    public String winner(Squad squadFirstS, Squad squadSecondS, String sixteen) {

        String resultSixteen = sixteen + ", " + squadFirstS.getTeamName() + ",  " + squadSecondS.getTeamName() + ", " + squadFirstS.getPoints() + " : " + squadSecondS.getPoints();
        if (squadFirstS.getPoints() == squadSecondS.getPoints()) {
            databaseClass.drawPoints(squadFirstS);
            databaseClass.drawPoints(squadSecondS);
            listOfTeams.delete(squadFirstS);
        } else if (squadFirstS.getPoints() > squadSecondS.getPoints()) {
            databaseClass.winnerPoints(squadFirstS);
            listOfTeams.delete(squadSecondS);
        } else if (squadFirstS.getPoints() < squadSecondS.getPoints()) {
            databaseClass.winnerPoints(squadSecondS);
            listOfTeams.delete(squadFirstS);
        }
        return resultSixteen;
    }


}

