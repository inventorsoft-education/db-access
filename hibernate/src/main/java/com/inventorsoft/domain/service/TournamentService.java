package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Tournament;
import com.inventorsoft.domain.repository.TournamentRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentService extends GeneralService<Tournament, Integer> {

    TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        super(tournamentRepository);
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional(readOnly = true)
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Transactional
    public Tournament createTournament(String name) {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        return tournamentRepository.save(tournament);
    }

}
