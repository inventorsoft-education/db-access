package co.inventorsoft.academy.springBootTask.repository;

import co.inventorsoft.academy.springBootTask.domain.entity.TournamentBracket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentBracketRepository {

    TournamentBracket save(TournamentBracket tb);

    List<TournamentBracket> findAll();

}
