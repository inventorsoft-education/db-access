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
public class Match {
    int id;
    int tournamentId;
    int firstTeamId;
    int secondTeamId;
    int round;
    String matchResult;

    public String toString(){
        return "id:" + id + " tourID:" + tournamentId + " firstTeam:" + firstTeamId +
                " secondTeam:" + secondTeamId +" round:"+round+" result:"+matchResult;
    }
}
