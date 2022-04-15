package com.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "matches")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    int id;

    @Column(nullable = false)
    int round;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Tournament tournament;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    Team firstTeam;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    Team secondTeam;

    @Column(nullable = false)
    String result;
}
