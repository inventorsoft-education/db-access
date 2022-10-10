package co.inventorsoft.academy.springBootTask.repository;

import co.inventorsoft.academy.springBootTask.domain.entity.TournamentBracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentBracketRepository extends
        JpaRepository<TournamentBracket, Integer> {

}
