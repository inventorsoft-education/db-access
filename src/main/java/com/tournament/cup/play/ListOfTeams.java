package com.tournament.cup.play;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListOfTeams {
    List<Squad> squads = new ArrayList<>();

    private static final Squad shakhtar = new Squad("Shakhtar", "Pyatov", "De Dzerbi");
    private static final Squad dynamo = new Squad("Dynamo", "Sydorchuk", "Lucesku");
    private static final Squad liverpool = new Squad("Liverpool", "Henderson", "Klopp");
    private static final Squad juventus = new Squad("Juventus", "Chiellini", "Allegri");
    private static final Squad milan = new Squad("Milan", "Ibra", "Pioli");
    private static final Squad atletico = new Squad("Atletico", "Koke", "Simeone");
    private static final Squad arsenal = new Squad("Arsenal", "Arteta", "Wenger");
    private static final Squad ajax = new Squad("Ajax", "Tadic", "Ten Haag");
    private static final Squad mu = new Squad("Manchester United", "Ronaldo", "Rangnick");
    private static final Squad mc = new Squad("Manchester City", "De Bruyne", "Pep");
    private static final Squad chelsea = new Squad("Chelsea", "Thiago Silva", "Thuchel");
    private static final Squad real = new Squad("Real Madrid", "Modric", "Anchelotti");
    private static final Squad barca = new Squad("Barcelona", "Busquets", "Xavi");
    private static final Squad bayern = new Squad("Bayern Munich", "Neuer", "Nagelsmann");
    private static final Squad psg = new Squad("PSG", "Marquinhos", "Pochettino");

    Scanner scanner = new Scanner(System.in);


    public void fullList() {
        boolean loop = true;
        do {
            try {
                System.out.print("Enter number of teams: ");
                int a = scanner.nextInt();
                if (a == 4) {
                    squads.add(shakhtar);
                    squads.add(dynamo);
                    squads.add(liverpool);
                    loop = false;
                } else if (a == 8) {
                    squads.add(shakhtar);
                    squads.add(dynamo);
                    squads.add(liverpool);
                    squads.add(juventus);
                    squads.add(milan);
                    squads.add(atletico);
                    squads.add(arsenal);
                    loop = false;
                } else if (a == 16) {
                    squads.add(shakhtar);
                    squads.add(dynamo);
                    squads.add(liverpool);
                    squads.add(juventus);
                    squads.add(milan);
                    squads.add(atletico);
                    squads.add(arsenal);
                    squads.add(ajax);
                    squads.add(mu);
                    squads.add(mc);
                    squads.add(chelsea);
                    squads.add(real);
                    squads.add(barca);
                    squads.add(bayern);
                    squads.add(psg);
                    loop = false;
                } else {
                    System.out.println("Number of teams should be 4, 8 or 16");
                }
            } catch (InputMismatchException e) {
                System.out.println("Number of teams should be 4, 8 or 16");
                scanner.nextLine();
                loop = true;
            }

        } while (loop);

    }


    public Boolean add(Squad squad) {

        return squads.add(squad);
    }

    public int size() {
        return squads.size();
    }

    public Squad teamIndex(Integer num) {
        return squads.get(num);
    }

    public void delete(Squad squad) {
        squads.remove(squad);
    }

}

