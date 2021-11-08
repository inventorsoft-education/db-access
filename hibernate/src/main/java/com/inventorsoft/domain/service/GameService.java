package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Game;
import com.inventorsoft.domain.model.Team;
import com.inventorsoft.domain.model.Tournament;
import com.inventorsoft.domain.repository.GameRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import org.springframework.data.repository.CrudRepository;
import com.inventorsoft.domain.model.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService extends GeneralService<Game, Integer> {

    GameRepository gameRepository;

    public GameService( GameRepository gameRepository) {
        super(gameRepository);
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Transactional
    public Game createTestGame(Team t, Team t2, Tournament tournament) {
        Game game = new Game();
        game.setTeamFirst(t);
        game.setTeamSecond(t2);
        game.setResult("1/2");
        game.setRound("final");
        game.setTournament(tournament);
        return gameRepository.save(game);
    }

    @Transactional
    public List<Game> createAll(List<Team> teams, Tournament tournament) {
        List<Game> games = start(teams);
        for (Game game: games) {
            game.setTournament(tournament);
        }
        return gameRepository.saveAll(games);
    }


    private List<Game> start(List<Team> teams) throws IllegalArgumentException {
        HashMap<String, List<Game>> listOfGames = new HashMap<>();
        List<Team> ttemp = new ArrayList<>(teams);

        Game gtemp;
        Random random = new Random();
        int one, two;
        int rmax = (int) (Math.log(ttemp.size()) / Math.log(2));

        for (int i = rmax; i >= 0; i--) {
            while (ttemp.size() >= 2) {

                do {
                    one = random.nextInt(ttemp.size());
                    two = random.nextInt(ttemp.size());
                } while (one <= two);

                gtemp = new Game();
                        gtemp.setTeamFirst(ttemp.get(one));
                        gtemp.setTeamSecond(ttemp.get(two));
                        gtemp.setRound(i != 1 ? "1/" + i : "final");
                        gtemp.setResult(one + ":" + two);

                addGame(gtemp, listOfGames);
                ttemp.remove(one);
                ttemp.remove(two);

            }
            ttemp = getWinners("1/" + i, listOfGames);
        }

        ttemp = getWinners("final", listOfGames);
        List<Game> gameList = new ArrayList<>();
        for (String s : listOfGames.keySet())
            gameList.addAll(listOfGames.get(s));
        return gameList;
    }

    private void addGame(Game game, HashMap<String, List<Game>> listOfGames) {
        listOfGames.compute(game.getRound(), (round, existingGames) -> {
            final List<Game> games = Objects.requireNonNullElseGet(existingGames, ArrayList::new);
            games.add(game);
            return games;
        });
    }

    private List<Team> getWinners(String round, HashMap<String, List<Game>> listOfGames) {
        if (!listOfGames.containsKey(round)) {
            return new ArrayList<>();
        }
        return listOfGames.get(round).stream().map(Game::Winner).collect(Collectors.toList());
    }
}
