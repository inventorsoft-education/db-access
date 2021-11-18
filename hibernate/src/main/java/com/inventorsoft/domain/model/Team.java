package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name = "TEAMS")
@EqualsAndHashCode
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false, length=50)
    String name;

    @Column(nullable = false, length=100)
    String captain;

    @Column(nullable = false, length=100)
    String coach;

    @Override
    public String toString() {
        return name +
                ", captain= " + captain +
                ", coach= " + coach;
    }
}
