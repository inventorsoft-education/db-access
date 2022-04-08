package com.tournament.cup.settings;

import com.tournament.cup.play.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentClass {
    final Tournament tournament;
    final LastClass lastClass;
    final WriterFile writerFile;
    final ListOfTeams listOfTeams;

    public void sixteen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRound , Team 1, Team 2, Score");
        switch (listOfTeams.size()) {
            case 2:
                String finalResultS = tournament.winner(listOfTeams.teamIndex(0), listOfTeams.teamIndex(1), "Final");
                System.out.println(finalResultS);
                writerFile.writer(finalResultS);
                lastClass.cupWinner();
                break;
            case 4:
                String halfFinal1S = tournament.winner(listOfTeams.teamIndex(2), listOfTeams.teamIndex(3), "1/2");
                String halfFinal2S = tournament.winner(listOfTeams.teamIndex(1), listOfTeams.teamIndex(0), "1/2");
                System.out.println(halfFinal1S);
                System.out.println(halfFinal2S);
                writerFile.writer(halfFinal1S + "\n" + halfFinal2S);
                break;
            case 8:
                String quarterFinal1S = tournament.winner(listOfTeams.teamIndex(6), listOfTeams.teamIndex(7), "1/4");
                String quarterFinal2S = tournament.winner(listOfTeams.teamIndex(4), listOfTeams.teamIndex(5), "1/4");
                String quarterFinal3S = tournament.winner(listOfTeams.teamIndex(3), listOfTeams.teamIndex(2), "1/4");
                String quarterFinal4S = tournament.winner(listOfTeams.teamIndex(0), listOfTeams.teamIndex(1), "1/4");
                System.out.println(quarterFinal1S);
                System.out.println(quarterFinal2S);
                System.out.println(quarterFinal3S);
                System.out.println(quarterFinal4S);
                writerFile.writer(quarterFinal1S + "\n" + quarterFinal2S + "\n" + quarterFinal3S + "\n" + quarterFinal4S);
                break;
            case 16:
                String quarterFinal5 = tournament.winner(listOfTeams.teamIndex(6), listOfTeams.teamIndex(7), "1/8");
                String quarterFinal6 = tournament.winner(listOfTeams.teamIndex(4), listOfTeams.teamIndex(5), "1/8");
                String quarterFinal7 = tournament.winner(listOfTeams.teamIndex(3), listOfTeams.teamIndex(2), "1/8");
                String quarterFinal8 = tournament.winner(listOfTeams.teamIndex(0), listOfTeams.teamIndex(1), "1/8");
                String quarterFinal9 = tournament.winner(listOfTeams.teamIndex(6), listOfTeams.teamIndex(7), "1/8");
                String quarterFinal10 = tournament.winner(listOfTeams.teamIndex(4), listOfTeams.teamIndex(5), "1/8");
                String quarterFinal11 = tournament.winner(listOfTeams.teamIndex(3), listOfTeams.teamIndex(2), "1/8");
                String quarterFinal12 = tournament.winner(listOfTeams.teamIndex(0), listOfTeams.teamIndex(1), "1/8");
                System.out.println(quarterFinal5);
                System.out.println(quarterFinal6);
                System.out.println(quarterFinal7);
                System.out.println(quarterFinal8);
                System.out.println(quarterFinal9);
                System.out.println(quarterFinal10);
                System.out.println(quarterFinal11);
                System.out.println(quarterFinal12);
                writerFile.writer(quarterFinal5 + "\n" + quarterFinal6 + "\n" + quarterFinal7 + "\n" + quarterFinal8 + "\n" + quarterFinal9 + "\n" + quarterFinal10 + "\n" + quarterFinal11 + "\n" + quarterFinal12);
                break;
            default:
                System.out.println("Invalid number of teams");

        }

        System.out.println("\nPlease choose number of option:\n" +
                "1.Next round \n" +
                "2.Logout");
        int b = scanner.nextInt();
        if (b == 1) {
            sixteen();
        } else if (b == 2) {
            System.out.println("Farewell");
            System.exit(0);
        } else {
            System.out.println("Just continue");
            sixteen();
        }
    }
}


