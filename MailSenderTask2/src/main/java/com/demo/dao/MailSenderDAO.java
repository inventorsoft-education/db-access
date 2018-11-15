package com.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.MailSender;

public interface MailSenderDAO {

	public void addSender(MailSender sender) throws Exception;
	
	public void updateSender(MailSender sender) throws Exception;
	
	public List<MailSender> getSenders() throws Exception;
	
	public MailSender getMailSender(int id);
	
	public void removeSender(int id) throws Exception;
	
	
}
