package main;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Race {
    int round;
    Team firstTeam;
    Team secondTeam;
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
        return "1/" + round + ", " + firstTeam.getTeamName() + ", " + secondTeam.getTeamName()+ ", " + time;
    }
}
