package com.example.demo.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DBConfig {
    static String URL = "jdbc:postgresql://localhost:5432/tournament";
    static String USERNAME = "postgres";
    static String PASSWORD = "postgres";
    @Getter
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
