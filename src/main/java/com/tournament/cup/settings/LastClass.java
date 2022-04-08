package com.tournament.cup.settings;

import com.tournament.cup.play.ListOfTeams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LastClass {

    private final ListOfTeams listOfTeams;
    private final DatabaseClass DatabaseClass;

    public void cupWinner() {
        String winnerSixteen = listOfTeams.teamIndex(0).getTeamName();
        System.out.println("\n            Congratulations " + winnerSixteen);
        DatabaseClass.showTable();
        System.out.println("Thank you for playing");
        DatabaseClass.delete();
        System.exit(0);
    }


}
