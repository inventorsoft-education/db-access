package com.example.demo.service;

import com.example.demo.repository.TeamsList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LastScreen {

    private final TeamsList teamsList;
    private final ClassForWorkingWithBD classForWorkingWithBD;

    public void winnerOfTournament() {
        String winner = teamsList.getTeamByIndex(0).getTeamName();
        System.out.println("\n____________The Winner of out tournament is " + winner);
        classForWorkingWithBD.showTournamentTable();
        System.out.println("\nGood bye");
        classForWorkingWithBD.delete();
        System.exit(0);
    }
}
