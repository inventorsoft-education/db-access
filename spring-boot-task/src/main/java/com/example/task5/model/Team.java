package com.example.task5.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.util.Objects;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public class Team {
    @NonFinal
    @Setter
    Integer id;
    String name;
    String captain;
    String coach;

    public Team(String name, String captain, String coach) {
        this.name = name;
        this.captain = captain;
        this.coach = coach;
    }

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
