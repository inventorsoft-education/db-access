package com.example.demo.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name="messages")
public class Message extends BaseEntity{

    @NotNull
    @Length(min = 3, message = "Subject must be at least 3 characters")
    @Column(name="subject")
    private String subject;

    @Email(message = "email fail")
    @Column(name="email_to")
    private String email_to;

    @NotEmpty(message = "Email text may not be empty")
    @Column(name="email_text")
    private String email_text;

    @NotNull
    @Column(name="future_second")
    private long future_second;
}

