package co.inventorsoft.academy.dao.intetface;

import co.inventorsoft.academy.model.Team;

import java.util.List;

public interface TeamDAOInterface {

    /**
     * This method add {@link Team} to database
     *
     * @param team input {@link Team}
     */
    void addTeam(Team team);

    /**
     * This method return count of {@link Team} in database
     *
     * @return count od teams
     */
    int size();

    /**
     * This method get {@link Team} from database by input index
     *
     * @param id input index
     * @return {@link Team}
     */
    Team getTeam(Integer id);

    /**
     * This method find id in database from input {@link Team}
     *
     * @param team input {@link Team}
     * @return id of {@link Team}
     */
    int getId(Team team);

    /**
     * This method create list of {@link Team} from database
     */
    List<Team> getTeams();


    /**
     * This method delete {@link Team} from database
     *
     * @param id input {@link Team} id
     */
    void deleteTeam(Integer id);

    /**
     * This method update {@link Team} by id
     *
     * @param id   input id of {@link Team}
     * @param team new value of {@link Team}
     */
    void updateTeam(Integer id, Team team);
}
