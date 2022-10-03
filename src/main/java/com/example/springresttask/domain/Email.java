package com.example.springresttask.domain;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Email {

    private Long id;

    private String recipientName;

    private String emailSubject;

    private String emailBody;

    private LocalDateTime deliveryDate;

    private boolean isSent;

}