package com.tournament.cup.settings;

import com.tournament.cup.play.Squad;
import com.tournament.cup.play.ListOfTeams;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@AllArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorizationClass {
    DatabaseClass databaseClass;
    ListOfTeams listOfTeams;

    public void squadSQL() {
        databaseClass.teamAuthorization("Shakhtar", "De Dzerbi", "Pyatov");
        databaseClass.teamAuthorization("Dynamo", "Lucesku", "Sydorchuk");
        databaseClass.teamAuthorization("Manchester United", "Rangnick", "Ronaldo");
        databaseClass.teamAuthorization("Manchester City", "Pep", "De Bruyne");
        databaseClass.teamAuthorization("Real Madrid", "Ancelotti", "Modric");
        databaseClass.teamAuthorization("Barcelona", "Xavi", "Pique");
        databaseClass.teamAuthorization("Chelsea", "Tuchel", "Jorginho");
        databaseClass.teamAuthorization("Liverpool", "Klopp", "Henderson");
        databaseClass.teamAuthorization("Bayern", "Nagelsmann", "Neuer");
        databaseClass.teamAuthorization("Inter", "Inzaghi", "Brozovic");
        databaseClass.teamAuthorization("PSG", "Pochettino", "Veratti");
        databaseClass.teamAuthorization("Arsenal", "Arteta", "Ghaka");
        databaseClass.teamAuthorization("Juventus", "Allegri", "Chiellini");
        databaseClass.teamAuthorization("Milan", "Pioli", "Ibra");
        databaseClass.teamAuthorization("Ajax", "Ten Haag", "Tadic");
    }

    public void register() {
        squadSQL();
        Scanner in = new Scanner(System.in);

        System.out.print("Enter name of Team: ");
        String teamName = in.nextLine();
        System.out.println("Team name is " + teamName);

        System.out.print("Enter captain's name: ");
        String captainName = in.nextLine();
        System.out.println("Captain's name is " + captainName);

        System.out.print("Enter coach's name: ");
        String coachName = in.nextLine();
        System.out.println("Coach's name is " + coachName);

        databaseClass.teamAuthorization(teamName, captainName, coachName);
        listOfTeams.add(new Squad(teamName, captainName, coachName));
        listOfTeams.fullList();

    }
}
