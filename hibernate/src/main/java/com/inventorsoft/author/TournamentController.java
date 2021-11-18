package com.inventorsoft.author;

import com.inventorsoft.domain.model.Game;
import com.inventorsoft.domain.model.Team;
import com.inventorsoft.domain.model.Tournament;
import com.inventorsoft.domain.service.GameService;
import com.inventorsoft.domain.service.TeamService;
import com.inventorsoft.domain.service.TournamentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentController {

    TeamService teamService;
    GameService gameService;
    TournamentService tournamentService;

    @GetMapping(value = "/tournament")
    public ResponseEntity<List<Tournament>> getAllTournament() {
        return ResponseEntity.ok(tournamentService.getAll());
    }

    @GetMapping(value = "/tournament/{id}")
    public ResponseEntity<Tournament> getTournamentByID(@PathVariable Integer id) {
        return ResponseEntity.ok(tournamentService.getById(id));
    }

    @PostMapping(value = "/tournament/{name}")
    public ResponseEntity<Tournament> create(@PathVariable String name, @RequestBody ArrayList<Team> teams){
        Tournament tournament = tournamentService.createTournament(name);
        List<Team> rightTeams = teamService.setAll(teams);
        List<Game> games = gameService.createAll(rightTeams, tournament);
        return ResponseEntity.ok(tournamentService.getById(tournament.getId()));
    }
}
