package co.inventorsoft.academy.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "teams")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @ToString.Exclude
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String pilot1;
    @Column(nullable = false)
    String pilot2;

    public Team(String name, String pilot1, String pilot2) {
        this.name = name;
        this.pilot1 = pilot1;
        this.pilot2 = pilot2;
    }
}

