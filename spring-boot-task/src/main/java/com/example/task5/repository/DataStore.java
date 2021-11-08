package com.example.task5.repository;

import com.example.task5.model.Team;
import com.example.task5.model.Tournament;

import java.util.List;

public interface DataStore {

    List<Team> getTeams();
    List<Team> getTeam(Integer id);
    List<Tournament> getTournaments();
    List<Object> getTournament(Integer id);
    List<Object> createTournament(String name, List<Team> teams);

}
