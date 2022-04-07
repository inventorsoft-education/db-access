package jdbc;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private final String DB_URL = "jdbc:postgresql://localhost:5433/tournament";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";

    public JdbcUtils() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initDB();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private void initDB() {
        URL sqlResource = Main.class.getClassLoader().getResource("init.sql");
        File file = new File(sqlResource.getPath());
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(new String(Files.readAllBytes(Paths.get(file.getPath()))));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
