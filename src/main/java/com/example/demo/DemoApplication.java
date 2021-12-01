package com.example.demo;

import com.example.demo.console.ConsoleInOut;
import com.example.demo.model.Team;
import com.example.demo.tournament.Tournament;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DemoApplication implements CommandLineRunner {
    Tournament tournament;
    ConsoleInOut console;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        tournament.saveAllTeams(List.of(
                new Team("name1","captain1","coach1"),
                new Team("name2","captain2","coach2"),
                new Team("name3","captain3","coach3"),
                new Team("name4","captain4","coach4"),
                new Team("name5","captain5","coach5"),
                new Team("name6","captain6","coach6"),
                new Team("name7","captain7","coach7"),
                new Team("name8","captain8","coach8")
                ));

        tournament.createAndSaveMatchesBasedOnListOfTeams(tournament.getAllTeams());
        console.printMatchList(tournament.getAllMatches());
        console.printWinner();
    }
}
