package co.inventrosoft.hibernate_task.service;

import co.inventrosoft.hibernate_task.console.ConsoleParser;
import co.inventrosoft.hibernate_task.console.MatchResult;
import co.inventrosoft.hibernate_task.model.Match;
import co.inventrosoft.hibernate_task.model.Team;
import co.inventrosoft.hibernate_task.model.Tournament;
import co.inventrosoft.hibernate_task.repository.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class TournamentService {


    private final TournamentRepository tournamentRepository;


    @Transactional
    public int createTournament() {
        Tournament tournament = new Tournament();
        tournamentRepository.save(tournament);
        return tournament.getId();
    }



}
