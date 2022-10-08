package main;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DBDateStore {
    static String DB_URL = "jdbc:mysql://localhost:3306/tournament";
    static String USER = "root";
    static String PASSWORD = "71382451";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            initDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static void initDB() {
        executeSqlScript("initTables.sql", connection);
        executeSqlScript("insert.sql", connection);
    }

    public static void executeSqlScript(String filename, Connection conn) throws Exception {
        URL sqlResource = DBDateStore.class.getClassLoader().getResource(filename);
        File file = new File(sqlResource.getPath());
        Reader reader = new BufferedReader(new FileReader(file));
        log.info("Running script from file: " + file.getCanonicalPath());
        ScriptRunner sr = new ScriptRunner(conn);
        sr.setAutoCommit(true);
        sr.setStopOnError(true);
        sr.runScript(reader);
        log.info("Done.");
    }
}
