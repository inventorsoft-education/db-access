package com.domain.service;

import com.domain.model.Team;
import com.domain.repository.TeamRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamService extends BaseService<Team,Integer>{

    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        super(teamRepository);
        this.teamRepository=teamRepository;
    }

    public List<Team> findByMatchId(int id){
        return teamRepository.findByMatchId(id);
    }

    public List<Team> findByTournamentId(int id){
        return teamRepository.findByTournamentId(id);
    }

}
