package co.inventorsoft.academy.dao.intetface;

import co.inventorsoft.academy.model.Tournament;

import java.util.List;

public interface TournamentDAOInterface {
    /**
     * This method add Tournament to database
     *
     * @param tournament input Tournament
     */
    void addTournament(Tournament tournament);


    /**
     * This method get Tournament from database by input index
     *
     * @param id input index
     * @return Tournament
     */
    Tournament getTournament(Integer id);

    /**
     * This method delete Tournament from database
     *
     * @param id input Tournament id
     */
    void deleteTournament(Integer id);

    /**
     * This method update Tournament by id
     *
     * @param id         input id of Tournament
     * @param Tournament new value of Tournament
     */
    void updateTournament(Integer id, Tournament Tournament);
}
