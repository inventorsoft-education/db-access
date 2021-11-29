package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "teams")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String captain;

    @Column(nullable = false)
    String coach;

    public Team(String name, String captain, String coach) {
        this.name = name;
        this.captain = captain;
        this.coach = coach;
    }
}
