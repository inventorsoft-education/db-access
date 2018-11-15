package com.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component
public class MailSenderRowMapper implements RowMapper<MailSender> {

	@Override
	public MailSender mapRow(ResultSet row, int rowNum) throws SQLException {
		
		MailSender mailSender = new MailSender();
		
		mailSender.setId(row.getInt("id"));
		mailSender.setRecipient(row.getString("recipient"));
		mailSender.setSubject(row.getString("subject"));
		mailSender.setBody(row.getString("body"));
		mailSender.setDate(row.getDate("date"));
		
		return mailSender;
	}

}
