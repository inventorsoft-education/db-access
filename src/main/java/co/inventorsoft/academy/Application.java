package co.inventorsoft.academy;

import co.inventorsoft.academy.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class Application implements CommandLineRunner {
    private final MainService mainScreen;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Start of my Application
     */
    @Override
    public void run(String... args) {

        mainScreen.mainScreen();
    }
}
