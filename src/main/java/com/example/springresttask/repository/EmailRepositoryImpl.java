package com.example.springresttask.repository;

import com.example.springresttask.domain.Email;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmailRepositoryImpl implements EmailRepository {

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    private ResultSet resultSet;

    Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("you have problems with connection");
        }
    }

    @Override
    public List<Email> findAllByPendingEmail() {

        List<Email> emails = new ArrayList<>();
        try {
            String sql = """
                        SELECT id,recipient_name,email_subject,email_body,delivery_date, is_sent
                        FROM email
                        WHERE is_sent IS FALSE;
                    """;
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Email email = new Email();
                email.setId(resultSet.getLong("id"));
                email.setRecipientName(resultSet.getString("recipient_name"));
                email.setEmailSubject(resultSet.getString("email_subject"));
                email.setEmailBody(resultSet.getString("email_body"));
                email.setDeliveryDate(resultSet.getTimestamp(5).toLocalDateTime());
                email.setSent(resultSet.getBoolean("is_sent"));
                emails.add(email);
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.info("there are no such letters");
        }


        return emails;
    }

    @Override
    public void deletePendingEmail(Long id) {

        String sql = """
                    DELETE
                    FROM email e
                    WHERE is_sent IS FALSE
                    AND  e.id=  ?;
                """;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            log.info("Deleting  is failed");
        }
    }

    @Override
    public Email save(Email email) {

        String sql = """
                    INSERT INTO email (recipient_name,email_subject,email_body,delivery_date,is_sent)
                    VALUES (?,?,?,?,?);
                """;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email.getRecipientName());
            ps.setString(2, email.getEmailSubject());
            ps.setString(3, email.getEmailBody());
            ps.setTimestamp(4, Timestamp.valueOf(email.getDeliveryDate()));
            ps.setBoolean(5, email.isSent());
            ps.executeUpdate();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            log.info("You cannot save this email");
            e.printStackTrace();
        }
        return email;
    }

    public Email updateDeliveryDate(Email email) {

        String sql = """
                UPDATE email set recipient_name = ?,email_subject = ?,email_body =? , delivery_date = ?, is_sent =  ?
                WHERE id =?;
                """;
        try {
            Connection connection = getConnection();
            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, email.getRecipientName());
            ps.setString(2, email.getEmailSubject());
            ps.setString(3, email.getEmailBody());
            ps.setTimestamp(4, Timestamp.valueOf(email.getDeliveryDate()));
            ps.setBoolean(5, email.isSent());
            ps.setLong(6, email.getId());
            ps.executeUpdate();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

}
