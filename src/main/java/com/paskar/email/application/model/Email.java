package com.paskar.email.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Email {

    int id;

    String recipient;

    String subject;

    String body;

    LocalDate date;

    public Email(String recipient, String subject, String body, LocalDate date) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.date = date;
    }
}
