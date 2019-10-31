package com.academy.task.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isEmailValid(String email) {
        Pattern emailPattern =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isTextValid(String text) {
        boolean check = true;
        if (text.length() < 2) {
            System.out.println("The " + text + " is very short, please try again");
            check = false;
        }

        return check;
    }

}
