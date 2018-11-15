package com.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.MailSender;
import com.demo.model.MailSenderRowMapper;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Transactional
@Repository
public class MailSenderDAOImpl implements MailSenderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public void addSender(MailSender sender) throws Exception {
		String sql = "INSERT INTO mailsender (id, recipient, subject, body, date) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, sender.getId(), sender.getRecipient(), sender.getSubject(), sender.getBody(), sender.getDate());
		
		/*sql = "SELECT id FROM mailsender WHERE recipient = ? and subject=?";
		int id = jdbcTemplate.queryForObject(sql, Integer.class, sender.getId(), sender.getSubject());
		
		sender.setId(id);*/
	}

	@Override
	public void updateSender(MailSender sender) throws Exception {
		String sql = "UPDATE mailsender SET recipient=?, subject=?, body=?, date=? WHERE id=?";
		jdbcTemplate.update(sql, sender.getRecipient(), sender.getSubject(), sender.getBody(), sender.getDate(), sender.getId());
		
	}

	@Override
	public List<MailSender> getSenders() throws Exception {
		
		String sql = "SELECT id, recipient, subject, body, date FROM mailSender";
        RowMapper<MailSender> rowMapper = new MailSenderRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
		
	}

	@Override
	public MailSender getMailSender(int id) {
		String sql = "SELECT id, recipient, subject, body, date FROM mailsender WHERE id = ?";
		RowMapper<MailSender> rowMapper = new BeanPropertyRowMapper<MailSender>(MailSender.class);
		MailSender mailSender = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return mailSender;
		
	}

	@Override
	public void removeSender(int id) throws Exception {
		String sql = "DELETE FROM mailsender WHERE id=?";
		jdbcTemplate.update(sql, id);
		
	}

	
	

	/*private List<MailSender> senders = null;
	saving into json file
	@PostConstruct
	public void loadSenders() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Resource resource = new ClassPathResource("list.json");

		File file = resource.getFile();
		senders = objectMapper.readValue(file, new TypeReference<List<MailSender>>() {});
		
		System.out.println("loadSenders()........");
	}
	
	@Override
	public List<MailSender> getSenders() throws Exception {	 
			
		System.out.println("getSenders().....");
		return senders;
	}
	
	@Override
	public MailSender getMailSender(int id) {
		 
		int index = senders.indexOf(new MailSender(id));
		MailSender sender = senders.get(index);
		
		return sender;
	}


	@Override
	public void removeSender(int id) throws Exception {
				
		senders.remove(getMailSender(id));	
		saveAll();
	}
	
	@Override
	public void addSender(MailSender sender) throws Exception {

		if (sender.getId() == 0) {
			int lastIndex = senders.size() - 1;
			int lastId = senders.get(lastIndex).getId();
			sender.setId(lastId + 1); 
		}
		
		senders.add(sender); 
		saveAll();
	}
	
	private void saveAll() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		Resource resource = new ClassPathResource("list.json");
		File file = resource.getFile();
        objectMapper.writeValue(file, senders);
	}
	
	@Override
	public void updateSender(MailSender sender) throws Exception {
		
		getMailSender(sender.getId()).setDate(sender.getDate());
		saveAll();		
	}*/

	
	

	


}
