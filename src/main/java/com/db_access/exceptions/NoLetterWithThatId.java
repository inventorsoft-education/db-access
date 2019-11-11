package com.db_access.exceptions;

public class NoLetterWithThatId extends RuntimeException {

    public NoLetterWithThatId(String message) {
        super(message);
    }
}
