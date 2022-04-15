package com.domain.service;

import com.domain.model.Tournament;
import com.domain.repository.TournamentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentService extends BaseService<Tournament, Integer> {

    TournamentRepository tournamentRepository;

    TournamentService(TournamentRepository tournamentRepository) {
        super(tournamentRepository);
        this.tournamentRepository = tournamentRepository;
    }

}
