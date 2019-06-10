package com.example.demo.service;

import com.example.demo.model.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        messageLists = messageService.getAllMessages();

        if (!messageLists.isEmpty()) {
            sendMessageInFuture();
        }
    }

    private void sendMessageInFuture() {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Message message : messageLists) {
            if (!message.isStatus()) {
                if (localDateTime.isAfter(message.getTimeStamp())) {
                    messageService.updateStatusById(message.getId(), true);
                    mailSender.send(message);
                }
            }
        }
    }
}
