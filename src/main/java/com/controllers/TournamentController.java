package com.controllers;

import com.domain.model.Tournament;
import com.domain.service.MatchService;
import com.domain.service.TeamService;
import com.domain.service.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TournamentController {
    TournamentService tournamentService;
    MatchService matchService;
    TeamService teamService;

    @GetMapping(value="/test")
    public String test(){
        return "test";
    }

}
