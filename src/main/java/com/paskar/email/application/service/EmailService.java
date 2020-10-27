package com.paskar.email.application.service;


import com.paskar.email.application.model.Email;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;
    private final EmailRepositoryJdbcImpl emailRepositoryJdbc;


    @Scheduled(fixedRate = 60000) //1 min
    public void sendSimpleEmail() throws MailException {
        List<Email> emailsNearDeliveryDate = emailRepositoryJdbc.findEmailsNearDeliveryDate();
        for (Email emails : emailsNearDeliveryDate) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emails.getRecipient());
            message.setSubject(emails.getSubject());
            message.setText(emails.getSubject());

            LOG.info("All information about your email {}:", emails.toString());
            this.emailSender.send(message);

            emailRepositoryJdbc.deleteById(emails.getId());
        }
    }
}
