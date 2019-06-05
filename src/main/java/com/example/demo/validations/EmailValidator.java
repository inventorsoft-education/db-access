package com.example.demo.validations;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidator {
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAdr = new InternetAddress(email);
            emailAdr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
