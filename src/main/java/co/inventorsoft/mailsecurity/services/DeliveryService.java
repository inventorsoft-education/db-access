package co.inventorsoft.mailsecurity.services;

import co.inventorsoft.mailsecurity.models.Email;
import co.inventorsoft.mailsecurity.repositories.EmailDao;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.logging.Logger;

public class DeliveryService {

    private EmailDao emailDao;

    private JavaMailSender mailSender;

    Logger logger = Logger.getLogger(DeliveryService.class.getName());

    @Scheduled(fixedRate  = 60000)
    private void sendEmail() {
        List<Email> emailsToSend = emailDao.mailsToSend();
        emailsToSend.forEach(email -> {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email.getRecipient());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getBody());
            logger.info("Log about mail:" + email.toString());
            mailSender.send(mailMessage);
        });
        emailsToSend.clear();
    }
}
