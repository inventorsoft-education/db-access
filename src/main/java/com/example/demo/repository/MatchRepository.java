package com.example.demo.repository;

import com.example.demo.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query(name = "matches.getAllWithTeams",
            value = "SELECT m from Match m " +
                    "left join fetch m.firstTeam " +
                    "left join fetch m.secondTeam")
    List<Match> getFullList();
}
