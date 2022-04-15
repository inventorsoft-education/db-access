package com.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(of="id")
@Table(name="tournaments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    int id;

    @Column(nullable = false)
    int rounds;
}
