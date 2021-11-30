package com.example.tournament.controller;

import com.example.tournament.model.Team;
import com.example.tournament.repository.TeamRepository;
import com.example.tournament.service.TeamService;
import com.example.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TournamentController {

    private TournamentService tournamentService;
    private TeamRepository teamRepository;

    @Autowired
    public TournamentController(TournamentService tournamentService, TeamRepository teamRepository){
        this.tournamentService = tournamentService;
        this.teamRepository = teamRepository;
    }


    @GetMapping("/launch")
    public String launchGame() throws Exception{
        tournamentService.launch();

        return "launch";
    }

    @PostMapping("/addTeam")
    public String addTeam(@RequestBody Team team){
        teamRepository.save(team);

        return team.getName();
    }

    @DeleteMapping("/deleteTeam/{id}")
    public String addTeam(@PathVariable("id") Integer id){
        if(teamRepository.findById(id).isPresent())
            teamRepository.deleteById(id);
        else{
            return "not found";
        }

        return "deleted";
    }

}
