package com.example.demo.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DBConfig {
    static String URL;
    static String USERNAME;
    static String PASSWORD;
    @Getter
    static Connection connection;

    static {
        URL = "jdbc:postgresql://localhost:5432/tournament";
        USERNAME = "postgres";
        PASSWORD = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
