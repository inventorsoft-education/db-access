package com.example.demo.model.enums.jdbc;


public enum JdbcConnect {
    USER_NAME("postgres"),
    PASSWORD("root"),
    CONNECTION_URL("jdbc:postgresql://localhost:5432/postgres"),
    POSTGRESQL_DRIVER("org.postgresql.Driver");

    String data;

    private JdbcConnect(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }
}

