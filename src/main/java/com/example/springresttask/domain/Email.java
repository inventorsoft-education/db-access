package com.example.springresttask.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Email {

    private Long id;

    private String recipientName;

    private String emailSubject;

    private String emailBody;

    private LocalDateTime deliveryDate;

    private Boolean isSent = false;

}