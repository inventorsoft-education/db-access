package co.inventorsoft.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Match {
    private String round;
    private int team1;
    private int team2;
    private int pointsTeam1;
    private int pointsTeam2;
}
