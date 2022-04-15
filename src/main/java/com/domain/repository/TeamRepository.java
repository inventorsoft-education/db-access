package com.domain.repository;

import com.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
    @Query("""
        select t from Team t
         inner join Match m on m.firstTeam=t or m.secondTeam=t
         where m.id=:id
    """)
    public List<Team> findByMatchId(@Param("id")int id);

    @Query("""
        select t from Team t
        inner join Match m on m.firstTeam=t or m.secondTeam=t
         where m.tournament.id=:id
    """)
    public List<Team> findByTournamentId(@Param("id") int id);
}
