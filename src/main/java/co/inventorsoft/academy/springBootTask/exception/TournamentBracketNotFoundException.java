package co.inventorsoft.academy.springBootTask.exception;

public class TournamentBracketNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Team is not found!";

    public TournamentBracketNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
