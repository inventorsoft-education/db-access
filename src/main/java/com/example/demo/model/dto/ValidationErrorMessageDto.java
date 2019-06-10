package com.example.demo.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorMessageDto {

    private List<ErrorMessageDto> errorMessageList = new ArrayList<>();

    public ValidationErrorMessageDto() {

    }
    public void addFieldError(String path, String message) {
        ErrorMessageDto errorMessage = new ErrorMessageDto(path, message);
        errorMessageList.add(errorMessage);
    }
}
