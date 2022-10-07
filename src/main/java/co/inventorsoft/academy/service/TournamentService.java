package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.Match;
import co.inventorsoft.academy.model.Tournament;
import co.inventorsoft.academy.repository.TournamentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TournamentService {

    TournamentRepository tournamentRepository;

    @Transactional
    public void createTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    @Transactional
    public List<String> getLastWinner() {
        Tournament winner = Optional.of(tournamentRepository.findFirstByOrderByIdDesc()).get();
        List<String> result = new ArrayList<>();
        result.add(winner.getName());
        Match match = winner.getMatch();
        result.add(match.getPointsTeam1() > match.getPointsTeam2()
                ? match.getTeam1().getName()
                : match.getTeam2().getName());
        result.add(winner.getDate().toString());
        return result;
    }
}
