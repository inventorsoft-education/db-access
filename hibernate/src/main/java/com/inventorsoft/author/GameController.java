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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Controller {

    TeamService teamService;
    GameService gameService;
    TournamentService tournamentService;

    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> games() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<Game> getGamesById(@PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getById(id));
    }

    @GetMapping(value = "/teams")
    public ResponseEntity<List<Team>> team() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> teams(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.getById(id));
    }

    @GetMapping(value = "/tournament")
    public ResponseEntity<List<Tournament>> tournament() {
        return ResponseEntity.ok(tournamentService.getAll());
    }

    @GetMapping(value = "/tournament/{id}")
    public ResponseEntity<Tournament> tournaments(@PathVariable Integer id) {
        return ResponseEntity.ok(tournamentService.getById(id));
    }

    @PostMapping(value = "/admin/new/tournament/{name}")
    public ResponseEntity<Tournament> createTournament(@PathVariable String name, @RequestBody ArrayList<Team> teams){
        Tournament tournament = tournamentService.createTournament(name);
        List<Team> rightTeams = teamService.setAll(teams);
        List<Game> games = gameService.createAll(rightTeams, tournament);

        return ResponseEntity.ok(tournamentService.getById(tournament.getId()));
    }



}
