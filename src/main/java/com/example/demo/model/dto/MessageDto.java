package com.example.demo.model.dto;

import com.example.demo.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private long id;
    private String subject;
    private String email_to;
    private String email_text;
    private long future_second;
    private Status status;
}
