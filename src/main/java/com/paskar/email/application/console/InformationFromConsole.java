package com.paskar.email.application.console;


import com.paskar.email.application.model.Email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InformationFromConsole {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");

    public Email createNewEmailFromConsole() throws IOException {
        String recipient = recipientValidation();
        String subject = emailSubject();
        String body = bodyValidation();
        LocalDate date = dateValidation();
        return new Email(recipient, subject, body, date);
    }

    public String recipientValidation() throws IOException {

        String emailRegEx = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        System.out.println("Enter the recipient's email address \nFor example: \"YourExample@gmail.com\"");
        while (true) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String recipient = reader.readLine();
                Pattern p = Pattern.compile(emailRegEx);
                Matcher m = p.matcher(recipient);
                if (m.find()) {
                    reader.close();
                    return recipient;
                }
            }
            System.err.println("E-mail is not correct, try again");
        }
    }

    public String emailSubject() throws IOException {
        System.out.println("Enter the subject of your email");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        }

    }

    public String bodyValidation() throws IOException {
        StringBuilder builder = new StringBuilder();
        System.out.println("Please enter your message\n"
                .concat("When you have finished writing your message, please press the ENTER once and type the word \"Exit\""));
        do {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String body = reader.readLine();
                if ("exit".equalsIgnoreCase(body)) {
                    return builder.toString();
                }
                builder.append(body).append(". ");
            }
        } while (true);
    }

    public LocalDate dateValidation() throws IOException {
        LocalDate time = null;
        System.out.println("Pay attention to a date template, date has to be entered only in this format\n"
                .concat("month day year hours:minutes - for example - 09 12 2020 20:56\n")
                .concat("Enter date when it is necessary to send the letter:"));
        int count = 0;
        do {
            try {
                count = 0;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    time = LocalDate.parse(reader.readLine(), FORMATTER);
                }
            } catch (DateTimeParseException exception) {
                count++;
                System.err.println("Pay attention to a date template, and try again");
            }
        } while (count != 0);

        return time;
    }

    public int numberOfLettersValidation() throws IOException {
        System.out.println("How many letters do you want to send? (enter a number)");
        int numberOfLetters = 0;
        int count = 0;
        do {
            try {
                count = 0;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    numberOfLetters = Integer.parseInt(reader.readLine());
                }
            } catch (NumberFormatException e) {
                count++;
                System.err.println("You have entered an unknown character, use only digits");
            }
        } while (count != 0);
        return numberOfLetters;
    }
}
