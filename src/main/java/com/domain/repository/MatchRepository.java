package com.domain.repository;

import com.domain.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Integer> {
    @Query(value = """
        select m from Match m
        inner join m.tournament t
        where t.id=:id
    """)
    public List<Match> getByTournamentId(@Param("id")int id);
}
