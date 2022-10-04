package co.inventorsoft.academy.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "matches")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @ToString.Exclude
    Integer id;
    @Column(nullable = false)
    String round;
    @ManyToOne
    Team team1;
    @ManyToOne
    Team team2;
    @Column(nullable = false)
    Integer pointsTeam1;
    @Column(nullable = false)
    Integer pointsTeam2;

    public Match(String round, Team team1, Team team2, Integer pointsTeam1, Integer pointsTeam2) {
        this.round = round;
        this.team1 = team1;
        this.team2 = team2;
        this.pointsTeam1 = pointsTeam1;
        this.pointsTeam2 = pointsTeam2;
    }
}
