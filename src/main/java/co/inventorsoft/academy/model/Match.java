package co.inventorsoft.academy.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @NonNull
    @Column(nullable = false)
    String round;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team team1;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team team2;

    @NonNull
    @Column(nullable = false)
    Integer pointsTeam1;

    @NonNull
    @Column(nullable = false)
    Integer pointsTeam2;
}
