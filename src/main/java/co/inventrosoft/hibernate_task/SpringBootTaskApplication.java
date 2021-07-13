package co.inventrosoft.hibernate_task;


import co.inventrosoft.hibernate_task.service.TournamentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication(proxyBeanMethods = false)
@ConfigurationPropertiesScan
public class SpringBootTaskApplication implements CommandLineRunner {
    private final TournamentService tournamentService;

    public SpringBootTaskApplication(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Override
    public void run(String... args) throws Exception {
        tournamentService.start();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);
    }

}
