package com.example.demo.validations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateValidation {

    public static LocalDateTime parseStringToDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        return dateTime;
    }
}
