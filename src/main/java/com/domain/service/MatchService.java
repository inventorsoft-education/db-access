package com.domain.service;

import com.domain.model.Match;
import com.domain.model.Tournament;
import com.domain.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchService extends BaseService<Match,Integer>{

    MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository){
        super(matchRepository);
        this.matchRepository=matchRepository;
    }

    public List<Match> getByTournamentId(int id){
        return matchRepository.getByTournamentId(id);
    }
}
