package com.tournament.cup.play;

import lombok.Data;

@Data
public class Squad {
    private String teamName;
    private String captain;
    private String coach;
    private Integer points = (int) (Math.random() * 4);


    public Squad(String teamName, String captain, String coach) {
        this.teamName = teamName;
        this.captain = captain;
        this.coach = coach;

    }
}

