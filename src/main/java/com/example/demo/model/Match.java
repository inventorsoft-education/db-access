package com.example.demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "MATCHES")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    int round;

    @ManyToOne(fetch = FetchType.EAGER)
    Team team1;

    @ManyToOne(fetch = FetchType.EAGER)
    Team team2;

    String score;

    public Match(int round, Team team1, Team team2) {
        this.round = round;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Team getWinner() {
        Team winner;
        int score1 = (int) (Math.random() * 10);
        int score2 = (int) (Math.random() * 10);
        if (score1 == score2) {
            score1++;
        }
        winner = (score1 < score2) ? team1 : team2;
        score = score1 + ":" + score2;
        return winner;
    }

    @Override
    public String toString() {
        return String.format("1/%d, %s, %s, %s", round, team1.getName(), team2.getName(), score);
    }

}
