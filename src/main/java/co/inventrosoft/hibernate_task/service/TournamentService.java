package co.inventrosoft.hibernate_task.service;

import co.inventrosoft.hibernate_task.model.Tournament;
import co.inventrosoft.hibernate_task.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Transactional
    public int createTournament() {
        Tournament tournament = new Tournament();
        tournamentRepository.save(tournament);
        return tournament.getId();
    }
}
