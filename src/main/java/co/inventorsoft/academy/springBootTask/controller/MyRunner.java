package co.inventorsoft.academy.springBootTask.controller;

import co.inventorsoft.academy.springBootTask.service.impl.TeamServiceImpl;
import co.inventorsoft.academy.springBootTask.service.impl.TournamentBracketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    private final TeamServiceImpl teamServiceImpl;
    private final TournamentBracketServiceImpl tbService;

    private final ConfigurableApplicationContext context;

    @Override
    public void run(String... args) {
        teamServiceImpl.addTeams();
        tbService.makeResults();
        tbService.showResults();
        System.exit(SpringApplication.exit(context));
    }

}
