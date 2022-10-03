package co.inventorsoft.academy.dao.intetface;

import co.inventorsoft.academy.model.Tournament;

public interface TournamentDAOInterface {
    /**
     * This method add {@link Tournament} to database
     *
     * @param tournament input {@link Tournament}
     */
    void addTournament(Tournament tournament);
}
