package sql.model;

import java.util.List;

public class Team {

    private String name;

    private String coach;

    private String captain;

    public Team(String name, String coach, String captain){
        this.name = name;
        this.captain = captain;
        this.coach = coach;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
