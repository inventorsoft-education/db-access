package co.inventrosoft.springboottask.service;

import co.inventrosoft.springboottask.repository.TournamentRepositoryJdbcImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepositoryJdbcImpl tournamentRepository;

    public int createTournament() {
        return tournamentRepository.create();
    }
}
