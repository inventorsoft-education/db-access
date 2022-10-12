package com.mail.entities;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

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
