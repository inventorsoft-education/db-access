package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Team;
import com.inventorsoft.domain.model.Tournament;
import com.inventorsoft.domain.repository.TournamentRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TournamentService extends GeneralService<Tournament, Integer> {

    TournamentRepository tournamentRepository;

    public TournamentService( TournamentRepository tournamentRepository) {
        super(tournamentRepository);
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional(readOnly = true)
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Transactional
    public Tournament createTestTournament() {
        Tournament tournament = new Tournament();
        tournament.setName("tournament");
        return tournamentRepository.save(tournament);
    }
    @Transactional
    public Tournament createTournament(String name) {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        return tournamentRepository.save(tournament);
    }

}
