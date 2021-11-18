package com.example.task5.controllers;

import com.example.task5.model.Team;
import com.example.task5.model.Tournament;
import com.example.task5.repository.DataStore;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    DataStore dataStore;

    @GetMapping("/teams")
    public List<Team> teams() {
        return dataStore.getTeams();
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<List<Team>> team(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(dataStore.getTeam(id));
    }

    @GetMapping("/tournament")
    public List<Tournament> tournament() {
        return dataStore.getTournaments();
    }

    @GetMapping("/tournament/{id}")
    public List<Object> tournament(@PathVariable Integer id) {
        return dataStore.getTournament(id);
    }

    @PostMapping("/new/{name}")
    public List<Object> createTournament(@PathVariable String name, @RequestBody ArrayList<Team> teams) {
        return dataStore.createTournament(name, teams);
    }
}
