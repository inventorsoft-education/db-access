package com.example.task5.service;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    HashMap<String, List<Game>> listOfGames = new HashMap<>();

    private boolean isPowerOfTwo(int x) {
        return x != 0 && ((x & (x - 1)) == 0);
    }

    public List<Team> register(List<Team> teams) {
        while (!isPowerOfTwo(teams.size())) {
            if (teams.size() <= 3) {
                throw new IllegalArgumentException("Number of commands must be >= 4");
            }
            teams.remove(teams.size() - 1);
        }
        return teams;
    }

    public List<Game> start(List<Team> teams) throws IllegalArgumentException {

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

                gtemp = new Game(
                        ttemp.get(one),
                        ttemp.get(two),
                        i != 1 ? "1/" + i : "final",
                        one + ":" + two
                );

                addGame(gtemp);
                ttemp.remove(one);
                ttemp.remove(two);

            }
            ttemp = getWinners("1/" + i);
        }

        ttemp = getWinners("final");
        List<Game> gameList = new ArrayList<>();
        for (String s : listOfGames.keySet())
            gameList.addAll(listOfGames.get(s));
        return gameList;
    }

    private void addGame(Game game) {
        listOfGames.compute(game.getRound(), (round, existingGames) -> {
            final List<Game> games = Objects.requireNonNullElseGet(existingGames, ArrayList::new);
            games.add(game);
            return games;
        });
    }

    private List<Team> getWinners(String round) {
        if (!listOfGames.containsKey(round)) {
            return new ArrayList<>();
        }
        return listOfGames.get(round).stream().map(Game::Winner).collect(Collectors.toList());
    }
}

