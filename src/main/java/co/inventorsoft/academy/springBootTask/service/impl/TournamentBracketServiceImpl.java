package co.inventorsoft.academy.springBootTask.service.impl;

import co.inventorsoft.academy.springBootTask.domain.dto.TeamDto;
import co.inventorsoft.academy.springBootTask.domain.dto.TournamentBracketDto;
import co.inventorsoft.academy.springBootTask.domain.entity.TournamentBracket;
import co.inventorsoft.academy.springBootTask.domain.mapper.TournamentBracketMapper;
import co.inventorsoft.academy.springBootTask.repository.TournamentBracketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TournamentBracketServiceImpl {

    private final TournamentBracketRepository tournamentBracketRepository;

    TournamentBracketDto tbDto;
    private final TeamServiceImpl teamServiceImpl;
    String matches = "";

    public void makeResults() {
        List<TeamDto> teams = teamServiceImpl.listTeams();
        Collections.shuffle(teams);
        int numberOfTeams = teams.size();
        int numberOfRounds = (int) log2(numberOfTeams);
        List<TeamDto> currentTeams = teams;
        for (int i = 0; i < numberOfRounds; i++) {
            currentTeams = playRound(currentTeams);
        }
        String winner = "WINNER: " + currentTeams.get(0).toString();
        tbDto = new TournamentBracketDto(matches, winner);
        createTournamentBracket(tbDto);
    }

    private List<TeamDto> playRound(List<TeamDto> teams) {
        List<TeamDto> winners = new ArrayList<>();
        for (int i = 0; i <= teams.size() - 2; i += 2) {
            TeamDto team1 = teams.get(i);
            TeamDto team2 = teams.get(i + 1);
            int score1 = (int) (Math.random() * 5);
            int score2 = (int) (Math.random() * 5);
            while (score1 == score2) {
                score2 = (int) (Math.random() * 5);
            }
            if (score1 > score2) {
                winners.add(team1);
            } else {
                winners.add(team2);
            }
            int round = teams.size() / 2;
            String match = String.format("1/%d, %s, %s, %d:%d%n", round,
                    team1.getName(), team2.getName(), score1, score2);
            matches += match;
        }
        return winners;
    }

    private double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

    public void showResults() {
        List<TournamentBracketDto> results = listTournamentBrackets();
        results.stream().findFirst().ifPresent(t -> {
            System.out.println(t.getMatches());
            System.out.println(t.getWinner());
        });
    }

    public List<TournamentBracketDto> listTournamentBrackets() {
        log.info("Get all tournamentBrackets");
        List<TournamentBracket> tournamentBrackets = tournamentBracketRepository.findAll();
        return TournamentBracketMapper.INSTANCE.mapListOfTournamentBracketToListOfDto(tournamentBrackets);
    }

    public TournamentBracketDto createTournamentBracket(TournamentBracketDto tournamentBracketDto) {
        TournamentBracket tournamentBracket = TournamentBracketMapper.INSTANCE.mapDtoToModel(tournamentBracketDto);
        log.info("Creating tournamentBracket with {} id...", tournamentBracket.getId());
        tournamentBracket = tournamentBracketRepository.save(tournamentBracket);
        log.info("TournamentBracket with id {} successfully created", tournamentBracket.getId());
        return TournamentBracketMapper.INSTANCE.mapModelToDto(tournamentBracket);
    }

}
