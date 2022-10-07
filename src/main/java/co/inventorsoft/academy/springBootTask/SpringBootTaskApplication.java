package co.inventorsoft.academy.springBootTask;

import co.inventorsoft.academy.springBootTask.beans.Team;
import co.inventorsoft.academy.springBootTask.beans.TournamentBracket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);

        TournamentBracket tournamentBracket = new TournamentBracket();
        List<Team> allTeams = tournamentBracket.addTeams();
        tournamentBracket.makeResults(allTeams);
    }

}
