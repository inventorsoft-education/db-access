package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.dao.TeamDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static co.inventorsoft.academy.enums.ColorText.RESET;
import static co.inventorsoft.academy.enums.ColorText.RED;
import static co.inventorsoft.academy.enums.ColorText.GREEN;
import static co.inventorsoft.academy.enums.ColorText.BLUE;

@Component
@AllArgsConstructor
public class RegistrationService {
    /**
     * list with team on this tournament
     */
    private TeamDAO teamsList;

    /**
     * This method create teams and add pool of teams (LinkedList)
     */
    public void createTeam() {
        Scanner in = new Scanner(System.in);
        boolean exitFlag = true;
        while (exitFlag) {
            System.out.println(BLUE.getValue() + "Please choose number of option:\n" + " 1. Create team \n" +
                    " 2. Start tournament\n" + " 3. Exit" + RESET.getValue());
            String option = in.nextLine();
            switch (option) {
                /* Add new team to list  */
                case "1":
                    System.out.println("************************************************************" +
                            "********************************************************");
                    System.out.print(GREEN.getValue() + "Input name of Team: " + RESET.getValue());
                    String name = in.nextLine();
                    System.out.print(GREEN.getValue() + "Input pilot #1 name: " + RESET.getValue());
                    String pilot1 = in.nextLine();
                    System.out.print(GREEN.getValue() + "Input pilot #2 name: " + RESET.getValue());
                    String pilot2 = in.nextLine();
                    teamsList.add(new Team(name, pilot1, pilot2));
                    System.out.println("************************************************************" +
                            "********************************************************");
                    break;
                /* check team list and go to next step (start tournament) */
                case "2":
                    if (isPowerOfTwo(teamsList.size()) && teamsList.size() >= 4) {
                        exitFlag = false;
                    } else {
                        System.out.println("***********************************" + RED.getValue() +
                                " You input " + teamsList.size() + " teams please, input more teams! " +
                                RESET.getValue() + "************************************");
                    }
                    break;
                /*  Exit of application */
                case "3":
                    System.out.println("******************************************************" +
                            GREEN.getValue() + " Goodbye!" + RESET.getValue() +
                            " ****************************************************");
                    System.exit(0);
                    break;
                /*  Wrong input case */
                default:
                    System.out.println(RED.getValue() + "Wrong input parameter! Go to next step." + RESET.getValue());
                    exitFlag = false;
                    break;
            }
        }
        System.out.println("************************************************************" +
                "********************************************************");
    }

    /**
     * This method check number if it is a power of two number
     *
     * @param x input number
     * @return true - if number is a power of two, else - false
     */
    private boolean isPowerOfTwo(int x) {
        double i = Math.log(x) / Math.log(2);
        return i == Math.floor(i);
    }
}
