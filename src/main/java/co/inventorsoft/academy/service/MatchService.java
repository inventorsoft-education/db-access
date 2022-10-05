package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Match;
import co.inventorsoft.academy.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchService {

    MatchRepository matchRepository;

    @Transactional
    public void createMatch(Match match) {
        matchRepository.save(match);
    }
}
