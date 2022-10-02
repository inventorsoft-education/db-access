package co.inventorsoft.academy.dao.intetface;

import co.inventorsoft.academy.model.Team;

import java.util.List;

public interface TeamDAOInterface {

    /**
     * This method add Team to database
     *
     * @param team input Team
     */
    void addTeam(Team team);

    /**
     * This method return count of teams in database
     *
     * @return count od teams
     */
    int size();

    /**
     * This method get Team from database by input index
     *
     * @param id input index
     * @return Team
     */
    Team getTeam(Integer id);

    /**
     * This method find id in database from input Team
     *
     * @param team input Team
     * @return id of Team
     */
    int getId(Team team);

    /**
     * This method create list of teams from database
     */
    List<Team> getTeams();


    /**
     * This method delete Team from database
     *
     * @param id input Team id
     */
    void deleteTeam(Integer id);

    /**
     * This method update Team by id
     *
     * @param id   input id of Team
     * @param team new value of Team
     */
    void updateTeam(Integer id, Team team);
}
