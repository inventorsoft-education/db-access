package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private long id;
    private String subject;
    private String emailTo;
    private String emailText;
    private LocalDateTime timeStamp;
    private boolean status;
}
