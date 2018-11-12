package db.access;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {

    @Value("${spring.datasource.url}")
    private  String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
