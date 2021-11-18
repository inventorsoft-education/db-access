package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "GAMES")
@EqualsAndHashCode
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team teamFirst;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team teamSecond;

    @Column(nullable = false, length=50)
    String round;
    @Column(nullable = false, length=50)
    String result;

    @ManyToOne
    Tournament tournament;

    @Override
    public String toString() {
        return String.format("%x %s & %s %S", round, teamFirst.getName(), teamSecond.getName(), result);
    }

    public Team Winner() {
        //result (example 2:3)
        //in str having 2 and 3 as String
        String[] str = result.split(":");

        //creating arr to storing 2 and 3 as int
        int[] ints = new int[str.length];

        //cast to String -> Integer -> int
        for (int i = 0; i < str.length; i++) {
            ints[i] = Integer.valueOf(str[i]);
        }
        //if 2 > 3
        //      winner is first team
        // another winner is second team also if 2==3
        return ints[0] > ints[1] ? teamFirst : teamSecond;
    }
}
