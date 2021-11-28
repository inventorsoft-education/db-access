package com.example.demo.entity;

import lombok.Data;

@Data
//@Setter
//@Getter

public class Team {
    private String teamName;
    private String captain;
    private String coach;
    private Integer points = (int) (Math.random() * 10);

  //  public Team() {
    //}

    public Team(String teamName, String captain, String coach) {
        this.teamName = teamName;
        this.captain = captain;
        this.coach = coach;
    }
}

