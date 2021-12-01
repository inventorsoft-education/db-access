package com.example.demo.service;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.repository.MatchRepository;
import com.example.demo.service.base.GeneralService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchService extends GeneralService<Match, Integer> {
    MatchRepository matchRepository;

    public MatchService(MatchRepository repository) {
        super(repository);
        this.matchRepository = repository;
    }

    public String generateScore() {
        Random random = new Random();
        Integer first = random.nextInt(6), second = random.nextInt(6);
        while (first.equals(second)) {
            first = random.nextInt(6);
            second = random.nextInt(6);
        }
        return String.format("%d:%d", first, second);
    }

    public Team getWinner(Match match) {
        String tmpScore = match.getScore();
        int scoreFirstTeam = Integer.parseInt(tmpScore.substring(0, tmpScore.lastIndexOf(":")));
        int scoreSecondTeam = Integer.parseInt(tmpScore.substring(tmpScore.lastIndexOf(":") + 1));
        return (scoreFirstTeam > scoreSecondTeam) ? match.getFirstTeam() : match.getSecondTeam();
    }
}
