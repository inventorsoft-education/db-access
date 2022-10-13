package co.inventorsoft.academy.springBootTask.controller;

import co.inventorsoft.academy.springBootTask.service.TeamService;
import co.inventorsoft.academy.springBootTask.service.TournamentBracketService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    private final TeamService teamService;
    private final TournamentBracketService tbService;

    private final ConfigurableApplicationContext context;

    @Override
    public void run(String... args) {
        teamService.addTeams();
        tbService.makeResults();
        tbService.showResults();
        System.exit(SpringApplication.exit(context));
    }

}
