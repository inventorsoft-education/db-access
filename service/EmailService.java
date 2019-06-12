package com.example.demo.service;

import com.example.demo.model.Email;

import java.util.ArrayList;
import java.util.Date;

public interface EmailService {

    ArrayList<Email> getAllEmails();
    ArrayList<Email> getActiveEmails();
    void deleteById(Long id);
    Email addEmail(Email email);
    void updateDeliveryDateForEmailById(Long id, Date date);
    void updateIsSentStatusForEmailForByID(Long id);

}
