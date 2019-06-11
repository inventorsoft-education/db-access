package com.example.homework6.databases;

import com.example.homework6.EmailItem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class JdbcConnection implements EmailRepo {

    private Connection conn;

    private Connection getDbConnection() throws SQLException {

        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework6?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        this.conn.setAutoCommit(false);
        return this.conn;
    }

    @Override
    public void save(EmailItem emailItem) {
        save(emailItem, 0);
    }

    public void save(EmailItem emailItem, Integer id){
        try {
            PreparedStatement statement;
            if(id == 0){
                statement = this.getDbConnection().prepareStatement("INSERT INTO email(email_recipient, email_subject, email_body, delivery_date) VALUES (?,?,?,?)");
            }
            else {
                statement = this.getDbConnection().prepareStatement("UPDATE email SET email_recipient=?,email_subject=?,email_body=?,delivery_date=?, status_delivery=?   WHERE id=?");
                statement.setBoolean(5, false);
                statement.setInt(6, emailItem.getId());
            }
            statement.setString(1, emailItem.getEmailRecipient());
            statement.setString(2, emailItem.getEmailSubject());
            statement.setString(3, emailItem.getEmailBody());
            statement.setString(4, emailItem.getDeliveryDate());
            statement.executeUpdate();
            transactionService(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<EmailItem> findAll() {
        ArrayList<EmailItem> messages = new ArrayList<>();
        try {
            PreparedStatement statement = this.getDbConnection().prepareStatement("SELECT * FROM email");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmailItem emailItem = createEmailItem(resultSet);
                messages.add(emailItem);
            }

            transactionService(statement);
            return messages;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EmailItem findById(Integer emailId) {
        try {
            PreparedStatement statement = this.getDbConnection().prepareStatement("SELECT * FROM email WHERE id=?");
            statement.setInt(1, emailId);
            ResultSet resultSet = statement.executeQuery();
            EmailItem emailItem = createEmailItem(resultSet);
            transactionService(statement);
            return emailItem;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(EmailItem emailItem) {

        try {
            PreparedStatement statement = this.getDbConnection().prepareStatement("DELETE FROM email WHERE id=?");
            statement.setInt(1, emailItem.getId());
            statement.execute();
            transactionService(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void transactionService(PreparedStatement statement) throws SQLException{
        try {
            statement.getConnection().commit();
        } catch (SQLException e) {
            if (!statement.getConnection().isClosed()) {
                statement.getConnection().rollback();
            }
        } finally {
            statement.getConnection().close();
        }

    }

    private EmailItem createEmailItem(ResultSet resultSet){
        EmailItem emailItem = new EmailItem();
        try {
            if (resultSet.next()) {
                emailItem.setId(resultSet.getInt("id"));
                emailItem.setEmailRecipient(resultSet.getString("email_recipient"));
                emailItem.setEmailSubject(resultSet.getString("email_subject"));
                emailItem.setEmailBody(resultSet.getString("email_body"));
                emailItem.setDeliveryDate(resultSet.getString("delivery_date"));
                emailItem.setStatusDelivery(resultSet.getBoolean("status_delivery"));
            }
            return emailItem;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
