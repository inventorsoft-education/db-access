package com.example.demo.validations;

import java.util.Date;

public class DateValidation {

    public DateValidation() {
    }

    public static boolean dateTransformer(long date, long jdate) {
        Date current = new Date();
        return jdate + (date * 1000) <= current.getTime() ? true : false;
    }
}
