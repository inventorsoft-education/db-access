package co.inventorsoft.academy.jdbc;


import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

@Component
@Slf4j
public class MyJDBC {
    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/myDB";
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "23081991";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e){
            log.error(e.getMessage());
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                log.error(e.getMessage());
                log.error("SQLState: " + ((SQLException) e).getSQLState());
                log.error("Error Code: " + ((SQLException) e).getErrorCode());
                log.error("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    log.error("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @SneakyThrows
    public static void createTables(@NonNull String filename) {
        String initSql = getSqlCode(filename);
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(initSql);
        }
    }

    @SneakyThrows
    private static String getSqlCode(String filename) {
        URL sqlResource = MyJDBC.class.getClassLoader().getResource(filename);
        File file = new File(sqlResource.getPath());
        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
}