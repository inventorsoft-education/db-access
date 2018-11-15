package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.demo.model.MailSender;
import com.demo.model.ServiceResponse;
import com.demo.service.MailSenderService;

@RestController
public class MailSenderController {

	@Autowired
	private MailSenderService mailSenderService;
	
	List<MailSender> mailSenders = new ArrayList<>();
	
	@RequestMapping("/emails")
	public List<MailSender> getSenders() {
		
		List<MailSender> senders = mailSenderService.getAll();		
		return senders;
	}
	
	@RequestMapping("/senders/{senderId}")
	public MailSender getSender(@PathVariable int senderId) {		
		
		return mailSenderService.get(senderId);
	}
	
	@RequestMapping(value = "/senders", method = RequestMethod.PUT)
	public void updateSender(@RequestBody MailSender sender) {
		
		mailSenderService.update(sender);
	}
	
	@RequestMapping(value = "/senders/{senderId}", method = RequestMethod.DELETE)
	public void deleteSender(@PathVariable int senderId) {
		
		mailSenderService.remove(senderId);
	}
	
	@PostMapping("/saveMessage")
	public ResponseEntity<Object> addMessage(@RequestBody MailSender mailSender) {
		mailSenderService.add(mailSender);
		ServiceResponse<MailSender> response = new ServiceResponse<MailSender>("success", mailSender);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	//для json сохранения в файл
	/*@PostMapping("/saveMessage")
	public ResponseEntity<Object> addMessage(@RequestBody MailSender mailSender) {
		mailSenders.add(mailSender);
		ServiceResponse<MailSender> response = new ServiceResponse<MailSender>("success", mailSender);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	@GetMapping("/getAllMessages")
	public ResponseEntity<Object> getAllMessages() {
		ServiceResponse<List<MailSender>> response = new ServiceResponse<>("success", mailSenders);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	*
	*/
	
	@GetMapping("/getAllMessages")
	public ResponseEntity<Object> getAllMessages() {
		ServiceResponse<List<MailSender>> response = new ServiceResponse<>("success", mailSenderService.getAll());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}

