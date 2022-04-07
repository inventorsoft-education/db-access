package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    int id;
    String name;
    String captain;
    String coach;

    public String toString() {
        return "id:" + id + " name:" + name + " coach:" + coach + " cap:" + captain;
    }
}
