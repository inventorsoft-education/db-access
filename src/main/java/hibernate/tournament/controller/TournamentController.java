package hibernate.tournament.controller;

import hibernate.tournament.model.Team;
import hibernate.tournament.repository.TeamRepository;
import hibernate.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
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
