package com.academy.task.console;

import com.academy.task.model.Email;
import com.academy.task.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ConsoleProcess implements CommandLineRunner {

    private EmailService emailService;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    public ConsoleProcess(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) throws Exception {
        input();
    }

    private void input() throws IOException {
        System.out.println("Hi! Let's send something email!");

        Email email = new Email();
        email.setId(emailService.getLargestId() + 1);
        email.setRecipient(inputRecipient());
        email.setSubject(inputSubject());
        email.setBody(inputBody());
        email.setDate(inputDate());

        emailService.addEmail(email);

        System.out.println("Your email will be sent at " + inputDate().toLocalTime());
    }

    private String inputRecipient() throws IOException {
        System.out.println("Write recipient: ");

        String recipient = reader.readLine();

        if (!Validation.isEmailValid(recipient)) {
            System.out.println("Bad email, please try again");
            inputRecipient();
        }

        return recipient;
    }

    private String inputSubject() throws IOException {
        System.out.println("Write subject of the letter:");

        String subject = reader.readLine();

        if (!Validation.isTextValid(subject)) {
            inputSubject();
        }
        return subject;
    }

    private String inputBody() throws IOException {
        System.out.println("Write your message:");

        String body = reader.readLine();

        if (!Validation.isTextValid(body)) {
            inputBody();
        }

        return body;
    }

    private LocalDateTime inputDate() throws IOException {
        System.out.println("Write Date (dd.MM.yyyy HH:mm): ");

        return LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

}
