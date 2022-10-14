package com.mail.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class TextMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    int id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String from_who;
    @Column(nullable = false)
    String to_whom;

    @Column(nullable = false)
    String message;

    @Column(nullable = false)
    LocalDate date_posted;
}
