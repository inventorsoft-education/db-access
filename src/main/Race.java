package main;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "races")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    int round;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team firstTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Team secondTeam;

    @Column(nullable = false)
    String time;

    public Race(int round, Team firstTeam, Team secondTeam) {
        this.round = round;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public Team startRace() {
        int firstTeamTime = determineTime(90, 200);
        int secondTeamTime = determineTime(90, 200);
        time = firstTeamTime + " : " + secondTeamTime;
        return firstTeamTime < secondTeamTime ? firstTeam : secondTeam;
    }

    private int determineTime(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public String toString() {
        return String.format("1/%x, %s, %s, %s, ", round, firstTeam.getTeam_name(), secondTeam.getTeam_name(), time);
    }
}
