package com.example.demo.dao.impl;

import com.example.demo.dao.MessageRepository;
import com.example.demo.model.entity.Message;
import com.example.demo.model.enums.Status;
import com.example.demo.model.enums.jdbc.JdbcConnect;
import com.example.demo.model.enums.sql.SqlRequestMessages;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcConnection implements MessageRepository {

    private Connection dbConnection;

    @SneakyThrows
    private Connection getDbConnection() {
        Class.forName(JdbcConnect.POSTGRESQL_DRIVER.getData());
        this.dbConnection = DriverManager.getConnection(JdbcConnect.CONNECTION_URL.getData(), JdbcConnect.USER_NAME.getData(), JdbcConnect.PASSWORD.getData());
        this.dbConnection.setAutoCommit(false);
        return this.dbConnection;
    }

    @Override
    @SneakyThrows
    public void save(Message message) {
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_INSERT.getRequest());
        statement.setString(1, message.getSubject());
        statement.setString(2, message.getEmailTo());
        statement.setString(3, message.getMessage());
        statement.setLong(4, message.getFutureSecond());
        statement.setLong(5, new Date().getTime());
        statement.setString(6, String.valueOf(message.getStatus()));
        statement.executeUpdate();
        transaction(statement);
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
            message.setEmailTo(resultSet.getString("emailTo"));
            message.setMessage(resultSet.getString("message"));
            message.setFutureSecond(resultSet.getLong("futureSecond"));
            message.setCurrentTime(resultSet.getLong("currentTime"));
            message.setStatus(Status.valueOf(resultSet.getString("status")));
            messages.add(message);
        }

        transaction(statement);
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
            message.setEmailTo(resultSet.getString("emailTo"));
            message.setMessage(resultSet.getString("message"));
            message.setFutureSecond(resultSet.getLong("futureSecond"));
            message.setCurrentTime(resultSet.getLong("currentTime"));
            message.setStatus(Status.valueOf(resultSet.getString("status")));
        }

        transaction(statement);
        return message;

    }

    @Override
    @SneakyThrows
    public void updateEmailTimeById(Long id, Long time) {
        PreparedStatement preparedStatement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_UPDATE_TIME.getRequest());
        preparedStatement.setLong(1, time);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        transaction(preparedStatement);
    }

    @Override
    @SneakyThrows
    public void updateEmailStatusById(Long id, Status status) {
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_UPDATE_STATUS.getRequest());
        statement.setString(1, String.valueOf(status));
        statement.setLong(2, id);
        statement.executeUpdate();
        transaction(statement);
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        PreparedStatement statement = this.getDbConnection().prepareStatement(SqlRequestMessages.SQL_DELETE_MESSAGE_ID.getRequest());
        statement.setLong(1, id);
        statement.execute();
        transaction(statement);
    }

    private void transaction(PreparedStatement statement) throws SQLException {
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
