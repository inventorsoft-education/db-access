package com.paskar.email.application.service;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.EmailRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailRepositoryPureJdbcImpl implements EmailRepository {
    static String DB_URL = "jdbc:mysql://localhost:3306/academy_jdbc_homework";
    static String USER_LOGIN = "mysql";
    static String USER_PASSWORD = "mysql";

    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    private java.sql.Date convertToDateViaLocalDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private void setParams(Email email, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, email.getRecipient());
        preparedStatement.setString(2, email.getSubject());
        preparedStatement.setString(3, email.getBody());
        preparedStatement.setDate(4, convertToDateViaLocalDate(email.getDate()));
    }

    @Override
    public void save(Email email) {
        String sql = "INSERT INTO first_homework_with_jdbc (recipient, subject, body, date) VALUES (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER_LOGIN, USER_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParams(email, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Email getById(int id) {
        Email email = null;
        String sql = "SELECT `recipient`, `subject`, `body`, `date` FROM first_homework_with_jdbc WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER_LOGIN, USER_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String recipient = resultSet.getString("recipient");
                String subject = resultSet.getString("subject");
                String body = resultSet.getString("body");
                LocalDate localDate = convertToLocalDateViaSqlDate(resultSet.getDate("date"));
                email = new Email(recipient, subject, body, localDate);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return email;
    }

    @Override
    public List<Email> findAll() {
        List<Email> list = new ArrayList<>();
        String sql = "SELECT `id`,`recipient`, `subject`, `body`, `date` FROM first_homework_with_jdbc";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER_LOGIN, USER_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String recipient = resultSet.getString("recipient");
                String subject = resultSet.getString("subject");
                String body = resultSet.getString("body");
                LocalDate localDate = convertToLocalDateViaSqlDate(resultSet.getDate("date"));
                list.add(new Email(id, recipient, subject, body, localDate));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Email email) {
        String sql = "UPDATE first_homework_with_jdbc SET recipient = ?, subject = ?, body = ?, date = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER_LOGIN, USER_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            setParams(email, preparedStatement);
            preparedStatement.setInt(5, email.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM first_homework_with_jdbc WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER_LOGIN, USER_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void deleteByEmailByDate(LocalDate time) {
        String sql = "DELETE FROM first_homework_with_jdbc WHERE date = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER_LOGIN, USER_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, convertToDateViaLocalDate(time));
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Email> findEmailsNearDeliveryDate() throws IOException {
        LocalDate time = LocalDate.now();
        List<Email> emailList = findAll();
        List<Email> result = new ArrayList<>();

        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                result.add(email);
            }
        }
        return result;
    }


    @Override
    public void deletingMassagesThatWereSent() throws IOException {

    }

    @Override
    public void save(List<Email> email) throws IOException {

    }
}
