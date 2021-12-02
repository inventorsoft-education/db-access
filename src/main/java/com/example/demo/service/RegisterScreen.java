package com.example.demo.service;

import com.example.demo.entity.Team;
import com.example.demo.repository.TeamsList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@AllArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterScreen {
    TeamsList teamsList;
    DBService db;

    public void register() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input name of Team:");
        String teamName = in.nextLine();

        System.out.print("Input captain's name:");
        String captainName = in.nextLine();

        System.out.print("Input coach's name:");
        String coachName = in.nextLine();

        teamsList.add(new Team(teamName, captainName, coachName));
        teamsList.madeList();
        db.setTeams(teamsList.teams());
    }
}
