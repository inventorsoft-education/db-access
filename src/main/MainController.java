package main;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;

@Controller
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    Tournament tournament;
    final TeamDAO teamDAO;
    final RaceDAO raceDAO;

    public void playTournament() {
        System.out.println("        WELCOME TO THE TOURNAMENT ");
        tournament.setTeamList(teamDAO.getTeams());
        Team winner = tournament.start();
        tournament.getRaceList().forEach(raceDAO::addRace);
        System.out.println("Winner of tournament is : " + winner.toString());
    }
}
