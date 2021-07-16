package co.inventrosoft.springboottask.console;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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
