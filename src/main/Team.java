package main;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    int id;
    String teamName;
    String horse;
    String rider;

    @Override
    public String toString() {
        return teamName + ", horse - " + horse + ", rider - " + rider;
    }
}
