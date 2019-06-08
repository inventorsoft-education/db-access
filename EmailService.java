package com.example.demo;

import com.example.demo.repository.JDBCEmailRepository;
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
public class EmailService {

    SimpleMailMessage simpleMailMessage;
    JavaMailSender javaMailSender;
    ArrayList<Email> allEmailsList;
    JDBCEmailRepository jdbcEmailRepository;

    @Autowired
    public EmailService(JDBCEmailRepository jdbcEmailRepository,JavaMailSender javaMailSender) {
        this.jdbcEmailRepository=jdbcEmailRepository;
        this.javaMailSender=javaMailSender;
        simpleMailMessage=new SimpleMailMessage();
        allEmailsList=jdbcEmailRepository.getAll();
    }


    @Scheduled(fixedRate = 20000)
    public void lookingForCurrentEmails() {

        for (Email counter:allEmailsList){
            if (counter.getDeliveryDate().equals(new Date()) && !counter.getSended()){
                simpleMailMessage.setTo(counter.getRecepientName());
                simpleMailMessage.setSubject(counter.getEmailSubject());
                simpleMailMessage.setText(counter.getEmailBody());
                javaMailSender.send(simpleMailMessage);
                counter.setSended(true);
                jdbcEmailRepository.updateStatus(counter);
            }
        }

    }


}
