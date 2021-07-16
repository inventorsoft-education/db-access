package co.inventrosoft.springboottask.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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
