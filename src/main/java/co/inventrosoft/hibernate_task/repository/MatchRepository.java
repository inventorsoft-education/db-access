package co.inventrosoft.hibernate_task.repository;

import co.inventrosoft.hibernate_task.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query("select m from Match m where m.tournament.id = ?1 order by m.roundCode desc, m.order")
    List<Match> findAllByTournamentId(int tournamentId);

    Optional<Match> findByRoundCodeAndOrderAndTournamentId(int roundCode, int order, int tournamentId);

    @Query("select m from Match m where m.firstTeam.name = ?1 and m.secondTeam.name = ?2 and m.tournament.id = ?3")
    Match getByTeamNamesAndTournamentId(String firstTeamName, String secondTeamName, int tournamentId);
}
