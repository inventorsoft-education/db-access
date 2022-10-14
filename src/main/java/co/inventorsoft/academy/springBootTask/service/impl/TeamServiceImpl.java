package co.inventorsoft.academy.springBootTask.service.impl;

import co.inventorsoft.academy.springBootTask.domain.dto.TeamDto;
import co.inventorsoft.academy.springBootTask.domain.entity.Team;
import co.inventorsoft.academy.springBootTask.domain.mapper.TeamMapper;
import co.inventorsoft.academy.springBootTask.repository.TeamRepository;
import co.inventorsoft.academy.springBootTask.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public List<TeamDto> listTeams() {
        log.info("Get all teams");
        List<Team> teams = teamRepository.findAll();
        return TeamMapper.INSTANCE.mapListOfTeamToListOfDto(teams);
    }

    private boolean isPowerOfTwo(int number) {
        return Integer.bitCount(number) == 1;
    }

}
