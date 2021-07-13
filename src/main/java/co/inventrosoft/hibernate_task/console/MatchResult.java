package co.inventrosoft.hibernate_task.console;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchResult {
    String firstTeamName;
    String secondTeamName;

    int firstTeamResult;
    int secondTeamResult;

    public void swap() {
        String tempName = firstTeamName;
        firstTeamName = secondTeamName;
        secondTeamName = tempName;

        int tempResult = firstTeamResult;
        firstTeamResult = secondTeamResult;
        secondTeamResult = tempResult;
    }
}
