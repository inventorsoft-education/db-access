package com.mail.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class TextMessage {
    static int counter = 0;

    int id;
    String title;
    @NonNull
    String from;
    @NonNull
    String to;
    String message;
    LocalDate date;

    {
        counter++;
    }
    public TextMessage(String title, @NonNull String from, @NonNull String to, String message) {
        this.id = counter;
        this.title = title;
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = LocalDate.now();
    }

    public void update(TextMessage text) {
        this.from = from;
        this.message = message;
        this.title = title;
        this.to = to;
    }
}
