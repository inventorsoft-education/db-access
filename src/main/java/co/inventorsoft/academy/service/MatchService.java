package co.inventorsoft.academy.service;


import co.inventorsoft.academy.model.Match;
import co.inventorsoft.academy.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchService {

    MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void createMatch(Match match) {
        matchRepository.save(match);
    }
}
