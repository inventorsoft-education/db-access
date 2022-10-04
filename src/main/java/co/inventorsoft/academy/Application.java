package co.inventorsoft.academy;

import co.inventorsoft.academy.service.RegistrationService;
import co.inventorsoft.academy.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import static co.inventorsoft.academy.enums.ColorText.GREEN;
import static co.inventorsoft.academy.enums.ColorText.RESET;

@SpringBootApplication
@RequiredArgsConstructor
@Component
public class Application implements CommandLineRunner {
    /**
     * In this tournament service we start tournament and calculate winner
     */
    private final MainService tournament;

    /**
     * In this registration service we add new team to tournament
     */
    private final RegistrationService registration;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }

    /**
     * Start of my Application and display header
     */
    @Override
    public void run(String... args) {
        System.out.println("********************************************************************************************************************");
        System.out.println(GREEN.getValue() + "\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome!!");
        System.out.println("\tPlease, register your team to derby. Amount of teams should be power of 2 and at least 4(4, 8, 16, 32, etc)");
        System.out.println(RESET.getValue() + "********************************************************************************************************************");
        registration.createTeam();
        tournament.start();
    }
}
