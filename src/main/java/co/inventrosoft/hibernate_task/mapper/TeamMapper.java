package co.inventrosoft.hibernate_task.mapper;

import co.inventrosoft.hibernate_task.model.Team;

public class TeamMapper {
    public static String fromObjectToString(Team team) {
        String str = "undefined";
        if (team != null) {
            str = team.getName() + ". Capitan - " + team.getCaptain() + ", coach - " + team.getCoach();
        }
        return str;
    }
}
