package com.example.demo.dao.impl;

import com.example.demo.dao.MessageRepository;
import com.example.demo.model.entity.Message;
import com.example.demo.model.enums.sql.SqlRequestMessages;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcConnection implements MessageRepository {

    private Connection dbConnection;

    @SneakyThrows
    private Connection getDbConnection() {
        Class.forName("org.postgresql.Driver");
        this.dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        this.dbConnection.setAutoCommit(false);
        return this.dbConnection;
    }

    @Override
    @SneakyThrows
    public void save(Message message) {
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_INSERT.getRequest());
        statement.setString(1, message.getSubject());
        statement.setString(2, message.getEmailTo());
        statement.setString(3, message.getEmailText());
        statement.setString(4, message.getTimeStamp());
        statement.setBoolean(5, message.isStatus());
        statement.executeUpdate();
        transactionalAnalys(statement);
    }

    @Override
    @SneakyThrows
    public List<Message> getAllMessage() {
        List<Message> messages = new ArrayList();
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_SELECT_ALL.getRequest());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getLong("id"));
            message.setSubject(resultSet.getString("subject"));
            message.setEmailTo(resultSet.getString("email_to"));
            message.setEmailText(resultSet.getString("email_text"));
            message.setTimeStamp(resultSet.getString("time_stamp"));
            message.setStatus(resultSet.getBoolean("status"));
            messages.add(message);
        }

        transactionalAnalys(statement);
        return messages;
    }

    @Override
    @SneakyThrows
    public Message findById(Long id) {
        Message message = new Message();
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_SELECT_MESSAGE_ID.getRequest());
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            message.setId(resultSet.getLong("id"));
            message.setSubject(resultSet.getString("subject"));
            message.setEmailTo(resultSet.getString("email_to"));
            message.setEmailText(resultSet.getString("email_text"));
            message.setTimeStamp(resultSet.getString("time_stamp"));
            message.setStatus(resultSet.getBoolean("status"));
        }

        transactionalAnalys(statement);
        return message;

    }

    @Override
    @SneakyThrows
    public void updateEmailTimeById(Long id, String timeStamp) {
        PreparedStatement preparedStatement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_UPDATE_TIME.getRequest());
        preparedStatement.setString(1, timeStamp);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        transactionalAnalys(preparedStatement);
    }

    @Override
    @SneakyThrows
    public void updateEmailStatusById(Long id, boolean status) {
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_UPDATE_STATUS.getRequest());
        statement.setBoolean(1, status);
        statement.setLong(2, id);
        statement.executeUpdate();
        transactionalAnalys(statement);
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_DELETE_MESSAGE_ID.getRequest());
        statement.setLong(1, id);
        statement.execute();
        transactionalAnalys(statement);
    }

    private void transactionalAnalys(PreparedStatement statement) throws SQLException {
        try {
            statement.getConnection().commit();
        } catch (SQLException var6) {
            if (!statement.getConnection().isClosed()) {
                statement.getConnection().rollback();
            }
        } finally {
            statement.getConnection().close();
        }

    }
}
