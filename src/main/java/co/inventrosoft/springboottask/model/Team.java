package co.inventrosoft.springboottask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    int id;
    String name;
    String captain;
    String coach;

    public Team(String name, String captain, String coach) {
        this.name = name;
        this.captain = captain;
        this.coach = coach;
    }

    @Override
    public String toString() {
        return this.name + ". Capitan - " + this.captain + ", coach - " + this.coach;
    }
}
