package com.mail.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage {

    int id;

    String title;

    @NonNull
    String from;

    @NonNull
    String to;

    String message;

    LocalDate date;
}
