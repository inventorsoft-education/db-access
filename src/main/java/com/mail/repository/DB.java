package com.mail.repository;

import com.mail.entities.TextMessage;
import com.mail.repository.interfac.DBInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DB implements DBInterface {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static PreparedStatement ps = null;

    private Connection connection;

    {
        try {
            setConnection();
            createMails();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private void createMails() throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS mails.messages(id INT NOT NULL AUTO_INCREMENT, 
                title VARCHAR(45) NOT NULL, from_who VARCHAR(45) NOT NULL, to_whom VARCHAR(45) NOT NULL,  
                message VARCHAR(400) NOT NULL,
                date_posted DATE NOT NULL, 
                PRIMARY KEY (id))
                """;
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    @Override
    @SneakyThrows
    public void addMessage(TextMessage message) {
        String query = "INSERT INTO mails.messages(title, from_who, to_whom, message, dat) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, message.getTitle());
            ps.setString(2, message.getFrom());
            ps.setString(3, message.getTo());
            ps.setString(4, message.getMessage());
            ps.setDate(5, Date.valueOf(message.getDate()));
            ps.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public List<TextMessage> getAllMessages() {
        String query = "SELECT * FROM mails.messages";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            return resultSetToTextMessages(ps.executeQuery());
        }
    }

    @SneakyThrows
    private List<TextMessage> resultSetToTextMessages(ResultSet rs) {
        List<TextMessage> list = new ArrayList<>();
        try (rs) {
            while (rs.next()) {
                TextMessage tm = new TextMessage();
                tm.setDate(rs.getDate("date_posted").toLocalDate());
                tm.setMessage(rs.getString("message"));
                tm.setFrom(rs.getString("from_who"));
                tm.setId(rs.getInt("id"));
                tm.setTo(rs.getString("to_whom"));
                tm.setTitle(rs.getString("title"));
                list.add(tm);
            }
        }
        return list;
    }

    @Override
    @SneakyThrows
    public void updateTextMessage(int id, TextMessage textMessage) {
        String query = "UPDATE mails.messages SET from_who = ?, to_whom = ?, title = ?, message = ? WHERE id =  ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, textMessage.getFrom());
            ps.setString(2, textMessage.getTo());
            ps.setString(3, textMessage.getTitle());
            ps.setString(4, textMessage.getMessage());
            ps.setInt(5, textMessage.getId());
            ps.executeQuery();
        }
    }

    @Override
    @SneakyThrows
    public void deleteTextMessage(int id) {
        String query = "DELETE FROM mails.messages WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public TextMessage findTextMessage(int id) {
        String query = "SELECT * FROM mails.messages WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return resultSetToTextMessages(ps.executeQuery())
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Message not found!"));
        }
    }

}
