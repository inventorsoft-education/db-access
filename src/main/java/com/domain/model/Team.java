package com.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(of="id")
@Table(name="teams")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String captain;

    @Column(nullable = false)
    String coach;
}
