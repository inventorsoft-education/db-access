package com.example.demo.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainScreen {
    TournamentScreen tournamentScreen;
    RegisterScreen registerScreen;

    public void mainScreen() throws IOException {
        System.out.println("\n                                                   Welcome");
        System.out.println("                         Please register your team to participate in the our tournament");
        registerScreen.register();
        System.out.println("\n Please choose number of option:\n" +
                " 1. Start tournament \n" +
                " 2.Logout");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String option = reader.readLine();
            if (option.equals("1")) {
                tournamentScreen.round();
            } else if (option.equals("2")) {
                System.out.println("Good bye");
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("WRONG");
        }
    }
}

