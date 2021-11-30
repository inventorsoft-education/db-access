package sql.additional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/tournament";
    private static final String USER = "postgres";
    private static final String PASS = "root";
    private static Statement statement;

    public static void connect() throws Exception{

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            statement = connection.createStatement();
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static Statement getStatement(){
        return statement;
    }
}
