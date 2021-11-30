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
    ClassForWorkingWithBD classForWorkingWithBD;

    public void register() {
        createDB();
        Scanner in = new Scanner(System.in);

        System.out.print("Input name of Team:");
        String teamName = in.nextLine();

        System.out.print("Input captain's name:");
        String captainName = in.nextLine();

        System.out.print("Input coach's name:");
        String coachName = in.nextLine();

        classForWorkingWithBD.teamRegister(teamName, coachName, captainName);
        teamsList.add(new Team(teamName, captainName, coachName));
        teamsList.madeList();
    }

    public void createDB() {
        classForWorkingWithBD.teamRegister("Monday", "M", "N");
        classForWorkingWithBD.teamRegister("Tuesday", "T", "E");
        classForWorkingWithBD.teamRegister("Wednesday", "W", "D");
        classForWorkingWithBD.teamRegister("Thursday", "H", "R");
        classForWorkingWithBD.teamRegister("Friday", "F", "I");
        classForWorkingWithBD.teamRegister("Saturday", "S", "R");
        classForWorkingWithBD.teamRegister("Sunday", "S", "N");
    }
}
