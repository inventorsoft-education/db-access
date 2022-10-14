package co.inventorsoft.academy.springBootTask.service.impl;

import co.inventorsoft.academy.springBootTask.domain.dto.TeamDto;
import co.inventorsoft.academy.springBootTask.domain.entity.Team;
import co.inventorsoft.academy.springBootTask.domain.mapper.TeamMapper;
import co.inventorsoft.academy.springBootTask.exception.TeamNotFoundException;
import co.inventorsoft.academy.springBootTask.repository.TeamRepository;
import co.inventorsoft.academy.springBootTask.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    public static final int MINIMUM_TEAMS_NUMBER = 4;

    private final TeamRepository teamRepository;

    @Override
    public void addTeams() {
        int teamNumber = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isActiveScanner = true;
        while (isActiveScanner) {
            System.out.println("Amount of teams: " + teamNumber);
            System.out.println("Enter team (name, capitan, coach)");
            System.out.println("Enter 'stop' to finish adding teams");
            String currentLine = scanner.nextLine();
            if (currentLine.equals("stop")) {
                if (isPowerOfTwo(teamNumber) && teamNumber >= MINIMUM_TEAMS_NUMBER) {
                    isActiveScanner = false;
                } else {
                    System.out.println("Amount of teams should be power of 2 and at least 4");
                }
            } else {
                Team currentTeam = addTeam(currentLine);
                if (currentTeam != null) {
                    teamRepository.save(currentTeam);
                    teamNumber++;
                }
            }
        }
        scanner.close();
    }

    private Team addTeam(String currentLine) {
        String[] teamDetails = currentLine.split(", ");
        TeamDto teamDto;
        if (teamDetails.length != 3) {
            System.out.println("Invalid input");
            return null;
        } else {
            teamDto = new TeamDto(teamDetails[0], teamDetails[1], teamDetails[2]);
        }
        System.out.println("team added");
        return TeamMapper.INSTANCE.mapDtoToModel(teamDto);
    }

    @Override
    public TeamDto getTeam(Integer id) {
        log.info("Finding team by {} id...", id);
        Team team = teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
        log.info("Team with {} id is found", id);
        return TeamMapper.INSTANCE.mapModelToDto(team);
    }

    @Override
    public List<TeamDto> listTeams() {
        log.info("Get all teams");
        List<Team> teams = teamRepository.findAll();
        return TeamMapper.INSTANCE.mapListOfTeamToListOfDto(teams);
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team = TeamMapper.INSTANCE.mapDtoToModel(teamDto);
        log.info("Creating team with {} id...", team.getId());
        team = teamRepository.save(team);
        log.info("Team with id {} successfully created", team.getId());
        return TeamMapper.INSTANCE.mapModelToDto(team);
    }

    @Override
    @Transactional
    public TeamDto updateTeam(Integer id, TeamDto teamDto) {
        log.info("Updating team with {} id...", id);
        Team persistedTeam = teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
        TeamMapper.INSTANCE.updateTeamFromDto(persistedTeam, teamDto);
        Team storedTeam = teamRepository.save(persistedTeam);
        log.info("Team with id {} successfully updated", storedTeam.getId());
        return TeamMapper.INSTANCE.mapModelToDto(persistedTeam);
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
        log.info("Team with id {} was deleted", id);
    }

    private boolean isPowerOfTwo(int number) {
        return Integer.bitCount(number) == 1;
    }

}
