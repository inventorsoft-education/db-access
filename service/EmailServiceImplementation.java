package com.example.demo.service;

import com.example.demo.model.Email;
import com.example.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@Service
public class EmailServiceImplementation implements EmailService {

   private EmailRepository emailRepository;

    @Autowired
    public EmailServiceImplementation(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Override
    @Transactional
    public ArrayList<Email> getAllEmails() {

        return emailRepository.findAll();
    }

    @Override
    @Transactional
    public ArrayList<Email> getActiveEmails() {
        return emailRepository.getAllByIsSentIsFalse();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        emailRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Email addEmail(Email email) {

        if (email.getDeliveryDate().after(new Date())){
            return emailRepository.saveAndFlush(email);
        }
        else return null;

    }

    @Override
    @Transactional
    public void updateDeliveryDateForEmailById(Long id, Date date) {
        emailRepository.updateDeliveryDateForEmailByID(id,date);
    }

    @Override
    @Transactional
    public void updateIsSentStatusForEmailForByID(Long id) {
        emailRepository.updateIsSentStatusForEmailForByID(id);
    }
}
