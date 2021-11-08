package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "TOURNAMENT")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id ;

    @Column(nullable = false)
    String name;


    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
