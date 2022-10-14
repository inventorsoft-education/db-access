package co.inventorsoft.academy.springBootTask.exception;

public class TeamNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Team is not found!";

    public TeamNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
