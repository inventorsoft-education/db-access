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
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, nullable = false, length = 90)
    @javax.validation.constraints.Email
    String recipient;

    @Column(nullable = false, length = 90)
    String subject;

    @Column(nullable = false)
    String body;

    @Column(nullable = false)
    LocalDateTime date;

}