package com.academy.task.service;

import com.academy.task.model.Email;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailSender {

    EmailSenderService emailSenderService;

    EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void deliverEmails() {
        List<Email> emailList = emailService.getEmailToSent();

        emailList.forEach(emailSenderService::sendEmail);
    }

}

