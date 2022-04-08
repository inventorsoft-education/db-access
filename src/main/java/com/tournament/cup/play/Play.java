package com.tournament.cup.play;

import lombok.Data;

@Data
public class Play {
    private Squad squadFirst;
    private Squad squadSecond;
    private String round;


    public Play(Squad squadFirst, Squad squadSecond) {
        this.squadFirst = squadFirst;
        this.squadSecond = squadSecond;
    }
}
