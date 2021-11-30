package com.example.demo.service;

import com.example.demo.model.Match;
import com.example.demo.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchService {

    MatchRepository matchRepository;

    public List<Match> matchesToList() {
        return matchRepository.findAll();
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> saveMatches(List<Match> matches) {
        return matchRepository.saveAll(matches);
    }

    public Match getMatchById(int id) {
        return matchRepository.getById(id);
    }

    public void removeMatches() {
        matchRepository.deleteAll();
    }

}
