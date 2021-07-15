package co.inventrosoft.springboottask.repository;


import co.inventrosoft.springboottask.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Repository
@AllArgsConstructor
public class TeamRepositoryFileImpl implements TeamRepository{
    private static final String TEAMS_FILE = "teams.json";
    private final ObjectMapper mapper;

    private List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        try {
            teams = mapper.readValue(new File(TEAMS_FILE), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public void save(List<Team> teams) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(TEAMS_FILE, false)))) {
            mapper.writeValue(out, teams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Team team) {
        List<Team> teams = findAll();
        teams.add(team);
        save(teams);
    }

    @Override
    public void update(Team teamData) {
        List<Team> teams = findAll();
        teams.stream()
                .filter(team -> teamData.getName().equals(team.getName()))
                .findFirst()
                .ifPresent(team -> {
                    team.setName(teamData.getName());
                    team.setCoach(teamData.getCoach());
                    team.setCaptain(teamData.getCaptain());

                    save(teams);
                });
    }

    @Override
    public boolean isExist(String teamName) {
        return findAll().stream()
                .map(Team::getName)
                .anyMatch(teamName::equals);
    }
}
