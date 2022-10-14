package co.inventorsoft.academy.springBootTask.repository;

import co.inventorsoft.academy.springBootTask.domain.entity.TournamentBracket;

import java.util.List;

public interface TournamentBracketRepository {

    TournamentBracket save(TournamentBracket tb);

    List<TournamentBracket> findAll();

}
