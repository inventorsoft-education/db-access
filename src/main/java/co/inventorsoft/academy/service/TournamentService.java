package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Tournament;
import co.inventorsoft.academy.repository.TournamentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TournamentService {

    TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public void createTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public Tournament getLastWinner(){
        return tournamentRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).get(0);
    }
}
