package sql.src.main.java;

import additional.DataOperations;
import additional.DatabaseConnector;
import model.Tournament;

public class Main {

    public static void main(String[] args) throws Exception{
        DatabaseConnector.connect();
        DataOperations.clearTable();
        Tournament tournament = new Tournament();
        tournament.nextRound();
    }
}
