package com.example.demo.model;

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

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TEAMS")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String capitan;
    @Column(nullable = false)
    String coach;

    public Team(String name, String captain, String coach) {
        this.name = name;
        this.capitan = captain;
        this.coach = coach;
    }

    @Override
    public String toString() {
        return String.format("%s, captain - %s, coach - %s", name, capitan, coach);
    }
}
