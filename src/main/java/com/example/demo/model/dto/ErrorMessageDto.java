package com.example.demo.model.dto;

import lombok.Data;

@Data
public class ErrorMessageDto {

    private String field;

    private String message;

    public ErrorMessageDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
