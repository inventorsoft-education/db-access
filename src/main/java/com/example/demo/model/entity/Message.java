package com.example.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private long id;

    private String subject;

    private String emailTo;

    private String emailText;

    private String timeStamp;

    private boolean status;
}
