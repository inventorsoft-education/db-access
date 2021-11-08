package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import javax.persistence.*;
import java.util.Objects;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "TEAMS")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name) &&
                captain.equals(team.captain) &&
                coach.equals(team.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, captain, coach);
    }

    @Override
    public String toString() {
        return name +
                ", captain= " + captain +
                ", coach= " + coach;
    }
}
