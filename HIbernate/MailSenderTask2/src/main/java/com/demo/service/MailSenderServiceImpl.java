package com.demo.service;

import java.util.List;

import com.demo.repository.MailSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.demo.model.MailSender;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MailSenderServiceImpl implements MailSenderService {

	@Autowired
	private MailSenderRepository mailSenderRepository;


	@Override
	@Transactional
	public void add(MailSender sender) {

		mailSenderRepository.save(sender);

	}

	@Override
	@Transactional
	@Modifying
	public void update(MailSender sender) {

		mailSenderRepository.save(sender);

	}

	@Override
	@Transactional
	public List<MailSender> getAll() {

		return (List<MailSender>) mailSenderRepository.findAll();
	}

	@Override
	@Transactional
	public MailSender get(int id) {

		return (MailSender) mailSenderRepository.getOne(id);
	}

	@Override
	@Transactional
	public void remove(int id) {
		mailSenderRepository.deleteById(id);

	}

	@Transactional
	public void getMailSenderById(int id) {
		mailSenderRepository.findById(id);

	}

	@Override
	public String toString() {
		return "MailSenderServiceImpl{" +
				"mailSenderRepository=" + mailSenderRepository +
				'}';
	}
}
