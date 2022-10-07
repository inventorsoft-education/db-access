package co.inventorsoft.academy.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
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
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "matches")
@FieldDefaults( level = AccessLevel.PRIVATE)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String round;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team team1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team team2;

    @Column(nullable = false)
    Integer pointsTeam1;

    @Column(nullable = false)
    Integer pointsTeam2;

    public Match() {
    }

    public Match(@NonNull String round,
                 @NonNull Team team1,
                 @NonNull Team team2,
                 @NonNull Integer pointsTeam1,
                 @NonNull Integer pointsTeam2) {
        this.round = round;
        this.team1 = team1;
        this.team2 = team2;
        this.pointsTeam1 = pointsTeam1;
        this.pointsTeam2 = pointsTeam2;
    }
}
