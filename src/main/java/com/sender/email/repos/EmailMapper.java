package com.sender.email.repos;

import com.sender.email.models.Email;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmailMapper implements RowMapper<Email> {
    @Override
    public Email mapRow(ResultSet resultSet, int i) throws SQLException{
        Email email = new Email();

        email.setId(resultSet.getInt("id"));
        email.setRecipient(resultSet.getString("recipient"));
        email.setSubject(resultSet.getString("subject"));
        email.setBody(resultSet.getString("body"));
        email.setDeliveryDate(new Date(resultSet.getTimestamp("delivery_date").getTime()));
        email.setIsSent(resultSet.getBoolean("is_sent"));

        return email;
    }
}
