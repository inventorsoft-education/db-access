package co.inventorsoft.academy.springBootTask.service;

import co.inventorsoft.academy.springBootTask.domain.dto.TeamDto;

import java.util.List;

public interface TeamService {

    void addTeams();

    TeamDto getTeam(Integer id);

    List<TeamDto> listTeams();

    TeamDto createTeam(TeamDto teamDto);

    TeamDto updateTeam(Integer id, TeamDto teamDto);

    void deleteTeam(Integer id);

}
