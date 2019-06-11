package com.example.demo.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="messages")
public class Message extends BaseEntity {

    @NotNull
    @Length(min = 3, message = "Subject must be at least 3 characters")
    private String subject;

    @Email(message = "email fail")
    private String emailTo;

    @NotEmpty(message = "Email text may not be empty")
    private String emailText;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeStamp;
}

