package co.inventorsoft.academy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static co.inventorsoft.academy.enums.ColorText.RESET;
import static co.inventorsoft.academy.enums.ColorText.GREEN;

@Component
@AllArgsConstructor
public class MainService {
    /**
     * In this tournament service we start tournament and calculate winner
     */
    private TournamentService tournament;
    /**
     * In this registration service we add new team to tournament
     */
    private RegistrationService registration;

    /**
     * This method display table header
     */
    public void mainScreen() {
        System.out.println("************************************************************" +
                "********************************************************");
        System.out.println(GREEN.getValue() + "\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome!!");
        System.out.println("\tPlease, register your team to derby. Amount of teams should be power of 2 and at least 4(4, 8, 16, 32, etc)");
        System.out.println(RESET.getValue() + "************************************************************" +
                "********************************************************");
        registration.createTeam();
        tournament.start();
    }
}

