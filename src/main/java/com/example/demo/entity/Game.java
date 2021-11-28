package com.example.demo.entity;

import lombok.Data;
@Data
public class Game {
    private Team teamFirst;
    private Team teamSecond;
    private String round;


    public Game(Team teamFirst, Team teamSecond) {
        this.teamFirst = teamFirst;
        this.teamSecond = teamSecond;
    }
}
