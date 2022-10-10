package co.inventorsoft.academy.repository;

import co.inventorsoft.academy.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    Tournament findFirstByOrderByIdDesc();
}