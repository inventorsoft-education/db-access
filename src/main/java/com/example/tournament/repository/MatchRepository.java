package com.example.tournament.repository;

import com.example.tournament.model.MatchDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDto, Integer> {
    MatchDto getMatchById(Integer id);
}
