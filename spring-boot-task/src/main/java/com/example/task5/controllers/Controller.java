package com.example.task5.controllers;

import com.example.task5.model.Team;
import com.example.task5.model.Tournament;
import com.example.task5.repository.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;

@RestController
public class Controller {

    DataStore dataStore;

    @Autowired
    Controller(DataStore dataStore){
        this.dataStore = dataStore;
    }

    @GetMapping("/teams")
    public List<Team> teams() {
       return  dataStore.getTeams();
    }


    @GetMapping("/team/{id}")
    public ResponseEntity<List<Team>> team(@PathVariable(value = "id") Integer id) {

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
    public List<Object> createTournament(@PathVariable String name, @RequestBody ArrayList<Team> teams){
        return dataStore.createTournament(name , teams);
    }





}
