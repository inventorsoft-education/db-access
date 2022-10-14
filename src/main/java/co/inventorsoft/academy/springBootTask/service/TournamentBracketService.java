package co.inventorsoft.academy.springBootTask.service;

import co.inventorsoft.academy.springBootTask.domain.dto.TournamentBracketDto;

import java.util.List;

public interface TournamentBracketService {

    void makeResults();

    void showResults();

    TournamentBracketDto getTournamentBracket(Integer id);

    List<TournamentBracketDto> listTournamentBrackets();

    TournamentBracketDto createTournamentBracket(TournamentBracketDto tournamentBracketDto);

    void deleteTournamentBracket(Integer id);

}
