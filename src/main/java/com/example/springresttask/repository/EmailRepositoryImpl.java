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
import javax.annotation.PostConstruct;
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

    private static Connection connection;
    private ResultSet resultSet;

    @PostConstruct
    void init() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Email> findAllByPendingEmail() {

        List<Email> emails = new ArrayList<>();
        try {
            //todo:can it be shorter?
            String SQL = "select id,recipient_name,email_subject,email_body,delivery_date," +
                    "is_sent from Email e where e.is_sent = false";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Email email = new Email();
                email.setId(resultSet.getLong("id"));
                email.setRecipientName(resultSet.getString("recipient_name"));
                email.setEmailSubject(resultSet.getString("email_subject"));
                email.setEmailBody(resultSet.getString("email_body"));
                email.setDeliveryDate(resultSet.getTimestamp("delivery_date").toLocalDateTime());
                email.setIsSent(resultSet.getBoolean("is_sent"));
                emails.add(email);
            }
        } catch (SQLException e) {
            log.info("there are no such letters");
        }


        return emails;
    }

    @Override
    public void deletePendingEmail(Long id) {
        String sql = "delete from email e where e.is_sent =false  and  e.id=  ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            log.info("Deleting  is failed");
        }

    }

    @Override
    public Email save(Email email) {
        String sql = "INSERT INTO email (id,recipient_name,email_subject,email_body,delivery_date,is_sent)\n" +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, email.getId());
            ps.setString(2, email.getRecipientName());
            ps.setString(3, email.getEmailSubject());
            ps.setString(4, email.getEmailBody());
            ps.setTimestamp(5, Timestamp.valueOf(email.getDeliveryDate()));
            ps.setBoolean(6, email.getIsSent());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.info("You cannot save this email");
        }
        return email;
    }

    public Email updateDeliveryDate(Email email) {
        String SQL = "update email set recipient_name = ?, " +
                "email_subject = ?,email_body =? , delivery_date = ?, is_sent =  ? where id = ?";
        try {
            PreparedStatement ps =
                    connection.prepareStatement(SQL);

            ps.setString(1, email.getRecipientName());
            ps.setString(2, email.getEmailSubject());
            ps.setString(3, email.getEmailBody());
            ps.setTimestamp(4, Timestamp.valueOf(email.getDeliveryDate()));
            ps.setBoolean(5, email.getIsSent());
            ps.setLong(6, email.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

}
