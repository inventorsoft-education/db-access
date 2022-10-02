package co.inventorsoft.academy.dao.intetface;

import co.inventorsoft.academy.model.Match;

import java.util.List;

public interface MatchDAOInterface {
    /**
     * This method add Match to database
     *
     * @param match input Match
     * @return id of Match
     */
    int addMatch(Match match);


    /**
     * This method get Match from database by input index
     *
     * @param id input index
     * @return Match
     */
    Match getMatch(Integer id);


    /**
     * This method create list of matches from database
     */
    List<Match> getMatches();


    /**
     * This method delete Match from database
     *
     * @param id input Match id
     */
    void deleteMatch(Integer id);

    /**
     * This method update Match by id
     *
     * @param id    input id of Match
     * @param match new value of Match
     */
    void updateMatch(Integer id, Match match);
}
