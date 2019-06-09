package com.example.demo.service;

import com.example.demo.model.enums.Status;
import com.example.demo.validations.DateValidation;
import com.example.demo.model.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ShedulerService {

    @Autowired
    @Qualifier("messageServiceImpl")
    private MessageService messageService;

    private List<Message> messageLists;

    @Autowired
    private MailSenderService mailSender;

    @Scheduled(fixedDelay = 1000)
    private void dataCheck() {
        messageLists = messageService.getAllMessage();

        if (messageLists.size() != 0) {
            sendMessageInFuture();
        }
    }

    private void sendMessageInFuture() {
        for (Message message : messageLists) {
            if (message.getStatus().equals(Status.NOT_SENT)) {
                if (DateValidation.dateTransformer(message.getFuture_second(), message.getCurrentTime())) {
                    messageService.updateStatusById(message.getId(), String.valueOf(Status.SENT));
                    System.out.println(message.getStatus());
                    mailSender.send(message);
                }
            }
        }
    }
}
