package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Team;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static co.inventorsoft.academy.enums.ColorText.RESET;
import static co.inventorsoft.academy.enums.ColorText.RED;
import static co.inventorsoft.academy.enums.ColorText.GREEN;
import static co.inventorsoft.academy.enums.ColorText.BLUE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationService {
    /**
     * list with team on this tournament
     */
    TeamService teamService;

    ConfigurableApplicationContext context;


    /**
     * This method create teams and add pool of teams (LinkedList)
     */
    public void createTeam() {
        try (Scanner in = new Scanner(System.in)) {
            boolean exitFlag = true;
            while (exitFlag) {
                System.out.println(BLUE.getValue() + "Please choose number of option:\n" + " 1. Create team \n" + " 2. Start tournament\n" + " 3. Exit" + RESET.getValue());
                String option = in.nextLine();
                switch (option) {
                    /* Add new team to list  */
                    case "1" -> {
                        System.out.println("********************************************************************************************************************");
                        System.out.print(GREEN.getValue() + "Input name of Team: " + RESET.getValue());
                        String name = in.nextLine();
                        System.out.print(GREEN.getValue() + "Input pilot #1 name: " + RESET.getValue());
                        String pilot1 = in.nextLine();
                        System.out.print(GREEN.getValue() + "Input pilot #2 name: " + RESET.getValue());
                        String pilot2 = in.nextLine();
                        teamService.createTeam(new Team(name, pilot1, pilot2));
                        System.out.println("********************************************************************************************************************");
                    }
                    /* check team list and go to next step (start tournament) */
                    case "2" -> {
                        int size = teamService.getSize();
                        if (isPowerOfTwo(size) && size >= 4) {
                            exitFlag = false;
                        } else {
                            System.out.println("***********************************" + RED.getValue() + " You input " + size + " teams please, input more teams! " + RESET.getValue() + "************************************");
                        }
                    }
                    /*  Exit of application */
                    case "3" -> {
                        System.out.println("******************************************************" + GREEN.getValue() + " Goodbye!" + RESET.getValue() + " ****************************************************");
                        System.exit(SpringApplication.exit(context));
                    }
                    /*  Wrong input case */
                    default -> {
                        System.out.println(RED.getValue() + "Wrong input parameter! Go to next step." + RESET.getValue());
                        exitFlag = false;
                    }
                }
            }
            System.out.println("********************************************************************************************************************");
        }
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
