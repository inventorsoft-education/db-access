package com.example.demo.service;

import com.example.demo.dao.MessageRepository;
import com.example.demo.model.entity.Message;
import com.example.demo.model.enums.Status;
import com.example.demo.validations.DateValidation;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

        if (messageLists.size() != 0) {
            sendMessageInFuture();
        }
    }

    private void sendMessageInFuture() {
        for (Message message : messageLists) {
            if (message.getStatus().equals(Status.NOT_SENT)) {
                if (DateValidation.dateTransformer(message.getFutureSecond(), message.getCurrentTime())) {
                    messageRepository.updateEmailStatusById(message.getId() - 1, Status.SENT);
                    System.out.println(message.getCurrentTime());
                    messageService.send(message);
                }
            }
        }
    }
}
