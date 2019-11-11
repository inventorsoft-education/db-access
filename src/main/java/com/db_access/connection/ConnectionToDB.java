package com.db_access.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    @Value("${url}")
    private String url;
    @Value("${user}}")
    private String user;
    @Value("${password}")
    private String password;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException("Class not found", exception.getCause());
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e.getCause());
        }
    }
}
