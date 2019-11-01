package com.academy.task.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "recipient", unique = true, nullable = false, length = 90)
    @javax.validation.constraints.Email
    String recipient;

    @Column(name = "subject", nullable = false, length = 90)
    String subject;

    @Column(name = "body", nullable = false)
    String body;

    @Column(name = "delivery_date", nullable = false, length = 30)
    LocalDateTime date;

}