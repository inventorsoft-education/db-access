package com.example.tournament.service;

import com.example.tournament.model.Team;
import com.example.tournament.model.Tournament;
import com.example.tournament.repository.TeamRepository;
import com.example.tournament.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService extends GeneralService<Tournament, Integer>{

    private TournamentRepository tournamentRepository;
    private TeamRepository teamRepository;
    private MatchService matchService;
    private List<Team> teams;
    private List<Team> teamsForRound;
    private Team winner;

    @Autowired
    public TournamentService(CrudRepository<Tournament, Integer> repository, TournamentRepository tournamentRepository, TeamRepository teamRepository, MatchService matchService) {
        super(repository);
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.matchService = matchService;
    }


    public void launch() throws Exception{
        teams = teamRepository.findAll();
        teamsForRound = new ArrayList<>();
        if(appropriateTeamNumber()){
            nextRound();
        }
    }

    public void generateMatches(String round) throws Exception{
        for(int i = 0; i < teams.size(); i++){
            teamsForRound.add(matchService.getResult(teams.get(i), teams.get(++i), round));
        }
        teams.clear();
        teams.addAll(teamsForRound);
        teamsForRound.clear();
        nextRound();
    }

    public void nextRound() throws Exception {
        String round;
        if (teams.size() >= 2){
            if (teams.size() == 2) {
                round = "FINAL";
            } else {
                round = "1/" + teams.size() / 2;
            }
            generateMatches(round);
        }
        else{
            winner = teams.get(0);
            tournamentRepository.save(new Tournament(winner.getName()));
        }
    }

    public boolean appropriateTeamNumber(){
        if(teams.size() % 2 == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
