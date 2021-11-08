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
@Table(name = "GAMES")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    Team teamFirst;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    Team teamSecond;

    @Column(nullable = false)
    String round;
    @Column(nullable = false)
    String result;

    @ManyToOne()
    Tournament tournament;

    @Override
    public String toString() {
        return round + " " + teamFirst.getName() +
                " & " + teamSecond.getName() + " " + result;
    }

    public Team Winner() {
        String[] str = result.split(":");
        int[] ints = new int[str.length];
        for (int i = 0; i < str.length; i++)
            ints[i] = Integer.valueOf(str[i]);
        if (ints[0] == ints[1]) return teamFirst;
        return ints[0] > ints[1] ? teamFirst : teamSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return teamFirst.equals(game.teamFirst) &&
                teamSecond.equals(game.teamSecond) &&
                round.equals(game.round) &&
                result.equals(game.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamFirst, teamSecond, round, result);
    }
}
