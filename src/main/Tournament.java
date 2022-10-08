package main;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tournament {
    LinkedList<Team> teamList;
    List<Race> raceList = new ArrayList<>();

    public Team start() {
        int teamListSize = teamList.size();
        int round = teamListSize / 2;
        if (isPowerOfTwo(teamListSize)) {
            Collections.shuffle(teamList);
            while (teamList.size() != 1) {
                Team firstTeam = teamList.pop();
                Team secondTeam = teamList.pop();
                Race race = new Race(round, firstTeam, secondTeam);
                Team winner = race.startRace();
                teamList.add(winner);
                raceList.add(race);
                System.out.println("Round, Team 1, Team 2, Time");
                System.out.println(race);
                System.out.println("Winner of 1/" + round + " round is " + winner.toString());
                if(teamList.size() == round) {
                    round /= 2;
                }
            }
        }
        return teamList.pop();
    }

    public boolean isPowerOfTwo(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }
}
