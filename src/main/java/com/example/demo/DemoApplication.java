package com.example.demo;

import com.example.demo.console.ConsoleInOut;
import com.example.demo.dao.TeamDAO;
import com.example.demo.model.Team;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final ConsoleInOut console;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        TeamDAO.save(List.of(
                new Team("name1","capitan1","coach1"),
                new Team("name2","capitan2","coach2"),
                new Team("name3","capitan3","coach3"),
                new Team("name4","capitan4","coach4"),
                new Team("name5","capitan5","coach5"),
                new Team("name6","capitan6","coach6"),
                new Team("name7","capitan7","coach7"),
                new Team("name8","capitan8","coach8")
                ));

        console.getTournament().createAndSaveMatchesBasedOnListOfTeams(TeamDAO.getList());
        console.outMatchList();
        console.outWinner();
    }
}
