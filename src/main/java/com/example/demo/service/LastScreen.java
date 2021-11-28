package com.example.demo.service;

import com.example.demo.repository.TeamsList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LastScreen {

    private TeamsList teamsList;
    private SQLMethods sqlMethods;

    public void winnerOfTournament() {
        String winner = teamsList.getTeamByIndex(0).getTeamName();
        System.out.println("\n____________The Winner of out tournament is " + winner);
        sqlMethods.showTournamentTable();
        System.out.println("\nGood bye");
        sqlMethods.delete();
        System.exit(0);
    }
}
