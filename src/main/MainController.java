package main;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import main.service.RaceService;
import main.service.TeamService;
import org.springframework.stereotype.Controller;

@Controller
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    Tournament tournament;
    TeamService teamService;
    RaceService raceService;

    public void playTournament() {
        System.out.println("        WELCOME TO THE TOURNAMENT ");
        tournament.setTeamList(teamService.getTeams());
        Team winner = tournament.start();
        tournament.getRaceList().forEach(raceService::addRace);
        System.out.println("Winner of tournament is : " + winner.toString());
    }
}
