package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@AllArgsConstructor
@Data
public class Match {
    private String firstTeam;
    private String secondTeam;
    private String round;
    private String score;

    public Match(String firstTeam, String secondTeam, int round, int cntRounds, String score) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        setRound(round, cntRounds);
        this.score = score;
    }

    public void setRound(int round, int cntRounds) {
        if ((round > cntRounds) || (round < 0))
            throw new IllegalArgumentException("round must be > 0 and <=" + cntRounds);
        else this.round = String.format("%d/%d", round, cntRounds);
    }

    public static String generateScore() {
        Random random = new Random();
        Integer first = random.nextInt(6), second = random.nextInt(6);
        while (first.equals(second)) {
            first = random.nextInt(6);
            second = random.nextInt(6);
        }
        return String.format("%d:%d", first, second);
    }

    public void setScore(int scoreFirstTeam, int scoreSecondTeam) {
        if ((scoreFirstTeam < 0 || scoreFirstTeam > 5) || (scoreSecondTeam < 0 || scoreSecondTeam > 5))
            throw new IllegalArgumentException("score must be >= 0 and <= 5");
        else this.score = Integer.toString(scoreFirstTeam + ':' + scoreSecondTeam);
    }

    public String getWinnerName() {
        int scoreFirstTeam = Integer.parseInt(score.substring(0, score.lastIndexOf(":")));
        int scoreSecondTeam = Integer.parseInt(score.substring(score.lastIndexOf(":") + 1));

        return (scoreFirstTeam > scoreSecondTeam) ? firstTeam : secondTeam;
    }
}
