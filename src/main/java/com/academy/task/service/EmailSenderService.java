package com.academy.task.service;

import com.academy.task.model.Email;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailSenderService {

    JavaMailSender mailSender;

    EmailService emailService;

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        mailSender.send(message);

        emailService.deleteEmail(email.getId());
    }

}
