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
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class RegisterScreen {
    TeamsList teamsList ;
    SQLMethods sqlMethods;

    public void register() {
        createDB();
        Scanner in = new Scanner(System.in);

        System.out.print("Input name of Team: ");
        String teamName = in.nextLine();


        System.out.print("Input captain's name: ");
        String captainName = in.nextLine();

        System.out.print("Input coach's name: ");
        String coachName = in.nextLine();


        sqlMethods.teamRegister(teamName,coachName,captainName);
        teamsList.add(new Team(teamName,captainName,coachName));
        teamsList.madeList();


    }
    public void createDB(){
        sqlMethods.teamRegister("Monday","M","N");
        sqlMethods.teamRegister("Tuesday","T","E");
        sqlMethods.teamRegister("Wednesday","W","D");
        sqlMethods.teamRegister("Thursday","H","R");
        sqlMethods.teamRegister("Friday","F","I");
        sqlMethods.teamRegister("Saturday","S","R");
        sqlMethods.teamRegister("Sunday","S","N");
    }
}
