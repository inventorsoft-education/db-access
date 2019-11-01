package com.lelek.dbAccess.service;

import com.lelek.dbAccess.dao.MessageDao;
import com.lelek.dbAccess.dto.MessageDto;
import com.lelek.dbAccess.model.MySimpleMailMessage;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Service
@AllArgsConstructor
public class SenderService extends Thread {

    private static boolean stop = false;
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private JavaMailSender javaMailSender;

    private MessageDao messageDao;

    private MySimpleMailMessage mapToSimpleMailMessage(MessageDto messageDto) {
        MySimpleMailMessage mySimpleMailMessage = new MySimpleMailMessage();
        mySimpleMailMessage.setId(messageDto.getId());
        mySimpleMailMessage.setTo(messageDto.getTo());
        mySimpleMailMessage.setCc(messageDto.getTo());
        mySimpleMailMessage.setBcc(messageDto.getTo());
        mySimpleMailMessage.setSubject(messageDto.getSubject());
        mySimpleMailMessage.setText(messageDto.getText());
        try {
            mySimpleMailMessage.setSentDate(format.parse(messageDto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mySimpleMailMessage.setSent(messageDto.isSent());
        return mySimpleMailMessage;
    }

    private MessageDto mapToDto(MySimpleMailMessage mySimpleMailMessage) {
        return MessageDto.builder()
                .id(mySimpleMailMessage.getId())
                .sent(mySimpleMailMessage.isSent())
                .to(Objects.requireNonNull(mySimpleMailMessage.getTo())[0])
                .subject(mySimpleMailMessage.getSubject())
                .text(mySimpleMailMessage.getText())
                .date(format.format(mySimpleMailMessage.getSentDate()))
                .build();
    }

    private void send() throws IOException {
        List<MySimpleMailMessage> allMessages = messageDao.getMessages().stream()
                .map(this::mapToSimpleMailMessage)
                .collect(Collectors.toList());
        for (MySimpleMailMessage message : allMessages) {
            if (!message.isSent()) {
                if (Objects.requireNonNull(message.getSentDate()).getTime() <= System.currentTimeMillis()) {
                    javaMailSender.send(message);
                    message.setSent(true);
                    messageDao.updateMessage(message.getId(), mapToDto(message));
                }
            }
        }
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                send();
                Thread.sleep(5000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
