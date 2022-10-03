package co.inventorsoft.academy.dao.intetface;

import co.inventorsoft.academy.model.Match;

public interface MatchDAOInterface {
    /**
     * This method add {@link Match} to database
     *
     * @param match input {@link Match}
     * @return id of {@link Match}
     */
    int addMatch(Match match);

    /**
     * This method get {@link Match} from database by input index
     *
     * @param id input index
     * @return {@link Match}
     */
    Match getMatch(Integer id);
}
