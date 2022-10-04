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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tournament")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @ToString.Exclude
    Integer id;
    @Column(nullable = false)
    String name;
    @OneToOne
    Match match;
    @Column(nullable = false)
    LocalDate date;

    public Tournament(String name, Match match, LocalDate date) {
        this.name = name;
        this.match = match;
        this.date = date;
    }
}
