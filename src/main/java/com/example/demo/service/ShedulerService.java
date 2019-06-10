package com.example.demo.service;

import com.example.demo.dao.MessageRepository;
import com.example.demo.model.entity.Message;
import com.example.demo.validations.DateValidation;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
@AllArgsConstructor
public class ShedulerService {

    private MessageRepository messageRepository;
    private List<Message> messageLists;
    private MailSenderService messageService;

    @Scheduled(fixedDelay = 1000)
    private void dataCheck() {
        messageLists = messageRepository.getAllMessage();

        if (!messageLists.isEmpty()) {
            sendMessageInFuture();
        }
    }

    private void sendMessageInFuture() {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Message message : messageLists) {
            if (!message.isStatus()) {
                if(localDateTime.isBefore(DateValidation.parseStringToDate(message.getTimeStamp()))) {
                    messageRepository.updateEmailStatusById(message.getId(), true);
                    messageService.send(message);
                }
            }
        }
    }
}
