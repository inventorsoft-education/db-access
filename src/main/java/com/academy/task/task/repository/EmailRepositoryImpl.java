package com.academy.task.repository;

import com.academy.task.model.Email;
import com.academy.task.util.ConnectionProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailRepositoryImpl implements EmailRepository {

    ConnectionProperties properties;

    @SneakyThrows
    private Connection getDBConnection() {
        Class.forName(properties.getDriver());

        Connection connection = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPass());
        connection.setAutoCommit(false);

        return connection;
    }

    @Override
    @SneakyThrows
    public void add(Email email) {
        PreparedStatement statement = getDBConnection().prepareStatement("INSERT INTO email(id, recipient, subject, body, deliver_date) VALUES (?, ?, ?, ?, ?)");
        statement.setLong(1, Math.toIntExact(email.getId()));
        statement.setString(2, email.getRecipient());
        statement.setString(3, email.getSubject());
        statement.setString(4, email.getBody());
        statement.setTimestamp(5, Timestamp.valueOf(email.getDate()));

        statement.execute();

        transactionChecker(statement);
    }

    @SneakyThrows
    private void transactionChecker(PreparedStatement statement) {
        try {
            statement.getConnection().commit();
        } catch (SQLException ex) {
            if (!statement.getConnection().isClosed()) {
                statement.getConnection().rollback();
            }
        } finally {
            statement.getConnection().close();
        }
    }

    @Override
    @SneakyThrows
    public List<Email> getAll() {
        PreparedStatement statement = getDBConnection().prepareStatement("SELECT id, recipient, subject, body, deliver_date FROM email");

        ResultSet resultSet = statement.executeQuery();

        transactionChecker(statement);

        return getEmails(resultSet);
    }

    @SneakyThrows
    private List<Email> getEmails(ResultSet resultSet) {
        List<Email> emails = new ArrayList<>();

        while (resultSet.next()) {
            Email email = new Email();
            email.setId(resultSet.getLong(1));
            email.setRecipient(resultSet.getString(2));
            email.setSubject(resultSet.getString(3));
            email.setBody(resultSet.getString(4));
            email.setDate(resultSet.getTimestamp(5).toLocalDateTime());

            emails.add(email);
        }

        return emails;
    }

    @Override
    @SneakyThrows
    public Email getById(Long id) {
        PreparedStatement statement = getDBConnection().prepareStatement("SELECT id, recipient, subject, body, deliver_date FROM email WHERE id =" + id);
        statement.setLong(1, Math.toIntExact(id));

        ResultSet resultSet = statement.executeQuery();

        Email email = new Email();
        while (resultSet.next()) {
            email.setId(resultSet.getLong(1));
            email.setRecipient(resultSet.getString(2));
            email.setSubject(resultSet.getString(3));
            email.setBody(resultSet.getString(4));
            email.setDate(resultSet.getTimestamp(5).toLocalDateTime());
        }

        transactionChecker(statement);

        return email;
    }

    @Override
    @SneakyThrows
    public List<Email> getEmailsToSend() {
        PreparedStatement statement = getDBConnection().prepareStatement("SELECT id, recipient, subject, body, deliver_date FROM email WHERE deliver_date < NOW()");

        ResultSet resultSet = statement.executeQuery();

        transactionChecker(statement);

        return getEmails(resultSet);
    }

    @Override
    @SneakyThrows
    public void deleteSent(Long id) {
        PreparedStatement statement = getDBConnection().prepareStatement("DELETE FROM email WHERE id = ?");

        statement.setLong(1, Math.toIntExact(id));
        statement.execute();

        transactionChecker(statement);
    }

    @Override
    @SneakyThrows
    public void update(Long id, Email email) {
        PreparedStatement statement = getDBConnection().prepareStatement("UPDATE email SET  recipient = ?, subject = ?, body = ?, deliver_date = ? WHERE id = ?");

        statement.setString(1, email.getRecipient());
        statement.setString(2, email.getSubject());
        statement.setString(3, email.getBody());
        statement.setTimestamp(4, Timestamp.valueOf(email.getDate()));
        statement.setInt(5, Math.toIntExact(id));
        statement.execute();

        transactionChecker(statement);
    }

    @Override
    @SneakyThrows
    public Long getLargestId() {
        PreparedStatement statement = getDBConnection().prepareStatement("SELECT id FROM email ORDER BY id DESC LIMIT 1");

        ResultSet resultSet = statement.executeQuery();

        Long id = 0L;
        while (resultSet.next()) {
            id = resultSet.getLong(1);
        }

        transactionChecker(statement);

        return id;
    }

}