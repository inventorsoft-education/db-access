package com.mail.repository;

import com.mail.entities.TextMessage;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static PreparedStatement ps = null;
    private static Statement statement;

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
        String query = "CREATE TABLE IF NOT EXISTS mails.messages(id INT NOT NULL AUTO_INCREMENT, "
                + "title VARCHAR(45) NOT NULL, fr VARCHAR(45) NOT NULL, t VARCHAR(45) NOT NULL,  "
                + "message VARCHAR(400) NOT NULL,"
                + "dat DATE NOT NULL, "
                + "PRIMARY KEY (id))";
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    @SneakyThrows
    public void addMessage(TextMessage message) {
        String query = "INSERT INTO mails.messages(title, fr, t, message, dat) VALUES(?, ?, ?, ?, ?)";
        ps = connection.prepareStatement(query);
        ps.setString(1, message.getTitle());
        ps.setString(2, message.getFrom());
        ps.setString(3, message.getTo());
        ps.setString(3, message.getMessage());
        ps.setDate(3, Date.valueOf(message.getDate()));
        ps.executeUpdate();
    }

    @SneakyThrows
    public List<TextMessage> getAllMessages() {
        String query = "SELECT * FROM mails.messages";
        ps = connection.prepareStatement(query);
        return resultSetToTextMessages(ps.executeQuery());
    }

    @SneakyThrows
    private List<TextMessage> resultSetToTextMessages(ResultSet rs) {
        List<TextMessage> list = new ArrayList<>();
        while(rs.next()) {
            TextMessage tm = new TextMessage();
            tm.setDate(rs.getDate("dat").toLocalDate());
            tm.setMessage(rs.getString("message"));
            tm.setFrom(rs.getString("fr"));
            tm.setId(rs.getInt("id"));
            tm.setTo(rs.getString("t"));
            tm.setTitle(rs.getString("title"));
            list.add(tm);
        }
        return list;
    }

    @SneakyThrows
    public void updateTextMessage(int id, TextMessage textMessage) {
        String query = "UPDATE mails.messages SET fr = '" + textMessage.getFrom()
                + "', t = '" + textMessage.getFrom() + "', title = '" + textMessage.getTitle()
                + "', message = '" + textMessage.getMessage()
                + "' WHERE id = " + id;
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    @SneakyThrows
    public void deleteTextMessage(int id) {
        String query = "DELETE FROM mails.messages WHERE id =" + id;
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    @SneakyThrows
    public TextMessage findTextMessage(int id) {
        String query = "SELECT * FROM mails.messages + WHERE id = " + id;
        statement = connection.createStatement();
        Optional<TextMessage> op = Optional.of(resultSetToTextMessages( statement.executeQuery(query)).get(0));
        return op.orElseThrow(() -> new EntityNotFoundException("Message not found!"));
    }

}
