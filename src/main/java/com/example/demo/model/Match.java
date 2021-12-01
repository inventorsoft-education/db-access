package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "matches")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team firstTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team secondTeam;

    @Column(nullable = false)
    String round;

    @Column(nullable = false)
    String score;

    public void setRound(int round, int cntRounds) {
        if ((round > cntRounds) || (round < 0))
            throw new IllegalArgumentException("round must be > 0 and <=" + cntRounds);
        else this.round = String.format("%d/%d", round, cntRounds);
    }
}
