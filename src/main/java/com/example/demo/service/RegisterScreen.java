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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterScreen {
    TeamsList teamsList;
    BDService BDService;

    public void register() {
        createDB();
        Scanner in = new Scanner(System.in);

        System.out.print("Input name of Team:");
        String teamName = in.nextLine();

        System.out.print("Input captain's name:");
        String captainName = in.nextLine();

        System.out.print("Input coach's name:");
        String coachName = in.nextLine();

        BDService.teamRegister(teamName, coachName, captainName);
        teamsList.add(new Team(teamName, captainName, coachName));
        teamsList.madeList();
    }

    public void createDB() {
        BDService.teamRegister("Monday", "M", "N");
        BDService.teamRegister("Tuesday", "T", "E");
        BDService.teamRegister("Wednesday", "W", "D");
        BDService.teamRegister("Thursday", "H", "R");
        BDService.teamRegister("Friday", "F", "I");
        BDService.teamRegister("Saturday", "S", "R");
        BDService.teamRegister("Sunday", "S", "N");
    }
}
