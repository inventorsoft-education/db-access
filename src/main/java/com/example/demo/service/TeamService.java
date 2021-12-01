package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import com.example.demo.service.base.GeneralService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamService extends GeneralService<Team, Integer> {
    TeamRepository repository;

    public TeamService(TeamRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
