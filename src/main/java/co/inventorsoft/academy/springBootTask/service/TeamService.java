package co.inventorsoft.academy.springBootTask.service;

import co.inventorsoft.academy.springBootTask.domain.dto.TeamDto;

import java.util.List;

public interface TeamService {

    void addTeams();

    List<TeamDto> listTeams();

}
