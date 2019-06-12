package com.example.demo;

import com.example.demo.model.Email;
import com.example.demo.service.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;


@Service
@EnableScheduling
public class EmailDeliveryService {

    SimpleMailMessage simpleMailMessage;
    JavaMailSender javaMailSender;
    ArrayList<Email> allEmailsList;
    EmailServiceImplementation emailServiceImplementation;

    @Autowired
    public EmailDeliveryService(EmailServiceImplementation emailServiceImplementation,JavaMailSender javaMailSender) {
        this.emailServiceImplementation=emailServiceImplementation;
        this.javaMailSender=javaMailSender;
        simpleMailMessage=new SimpleMailMessage();
        allEmailsList=emailServiceImplementation.getAllEmails();
    }


    @Scheduled(fixedRate = 20000)
    public void lookingForCurrentEmails() {

        for (Email counter:allEmailsList){
            if (counter.getDeliveryDate().equals(new Date()) && !counter.getIsSent()){
                simpleMailMessage.setTo(counter.getRecipientName());
                simpleMailMessage.setSubject(counter.getEmailSubject());
                simpleMailMessage.setText(counter.getEmailBody());
                javaMailSender.send(simpleMailMessage);
                counter.setIsSent(true);
                emailServiceImplementation.updateIsSentStatusForEmailForByID(counter.getId());
            }
        }

    }


}
