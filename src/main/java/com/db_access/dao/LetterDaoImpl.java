package com.db_access.dao;

import com.db_access.connection.ConnectionToDB;
import com.db_access.entity.Letter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LetterDaoImpl implements BaseDao<Letter> {

    private static final String SELECT_QUERY = "SELECT letters.id, letters.recipient, letters.subject, letters.body, " +
            "letters.deliveryTime, FROM letters";

    private ConnectionToDB connection;

    private static final Logger LOGGER = LoggerFactory.getLogger(LetterDaoImpl.class);

    public LetterDaoImpl(ConnectionToDB connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Letter> getById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_QUERY +
                    " WHERE letters.id = ?");
            preparedStatement.setLong(1, id);
            try (ResultSet result = preparedStatement.executeQuery();
            ) {
                if (result.next()) {
                    return Optional.of(initLetter(result));
                }
                closeConnection(preparedStatement);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Letter> getByRecipient(String recipient) {
        List<Letter> letters = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_QUERY +
                    " WHERE letters.recipient = ?");
            preparedStatement.setString(1, recipient);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    letters.add(initLetter(result));
                }
                closeConnection(preparedStatement);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return letters;
    }

    @Override
    public List<Letter> getAll() {
        List<Letter> letters = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_QUERY);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    letters.add(initLetter(result));
                }
                closeConnection(preparedStatement);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return letters;
    }

    @Override
    public void save(Letter letter) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("INSERT INTO letters(" +
                    "id, recipient,subject,body,deliveryTime) VALUES (?,?,?,?,?)");
            insertLetterFields(letter, preparedStatement);
            preparedStatement.executeQuery();
            closeConnection(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateById(Letter letter) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("UPDATE letters SET " +
                    "id=?, recipient, subject=?, body=?,deliveryTime=?");
            insertLetterFields(letter, preparedStatement);
            preparedStatement.executeQuery();
            closeConnection(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("DELETE FROM letters WHERE letters.id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            closeConnection(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private Letter initLetter(ResultSet resultSet) throws SQLException {
        Letter letter = new Letter();
        letter.setId(resultSet.getLong("id"));
        letter.setBody(resultSet.getNString("body"));
        letter.setRecipient(resultSet.getNString("recipient"));
        letter.setDeliveryTime(resultSet.getTimestamp("deliveryTime").toLocalDateTime());
        return letter;
    }

    private void insertLetterFields(Letter letter, PreparedStatement statement) throws SQLException {
        statement.setLong(1, letter.getId());
        statement.setString(2, letter.getRecipient());
        statement.setString(3, letter.getSubject());
        statement.setString(4, letter.getBody());
        statement.setTimestamp(5, Timestamp.valueOf(letter.getDeliveryTime()));
    }

    private void closeConnection(PreparedStatement statement) throws SQLException {
        try {
            statement.getConnection().commit();
            if (!statement.getConnection().isClosed())
                statement.getConnection().rollback();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage(), exception);
        } finally {
            statement.getConnection().close();
        }
    }
}
