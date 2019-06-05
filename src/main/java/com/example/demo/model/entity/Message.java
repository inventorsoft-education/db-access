package com.example.demo.model.entity;

import com.example.demo.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private long id;

    private String subject;

    private String emailTo;

    private String message;

    private long futureSecond;

    private long currentTime;

    private Status status;

}
