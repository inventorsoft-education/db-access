package com.lelek.dbAccess.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.dbAccess.WebApplication;
import com.lelek.dbAccess.dao.MessageDao;
import com.lelek.dbAccess.model.MySimpleMailMessage;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Setter
@Service
@AllArgsConstructor
public class SenderService extends Thread {

    private static boolean stop = false;

    private JavaMailSender javaMailSender;

    private MessageDao messageDao;

    private void send() throws IOException {
        List<MySimpleMailMessage> allMessages = messageDao.getMessages();
        for (MySimpleMailMessage message : allMessages) {
            if (!message.isSent()) {
                if (Objects.requireNonNull(message.getSentDate()).getTime() <= System.currentTimeMillis()) {
                    javaMailSender.send(message);
                    message.setSent(true);
                }
            }
        }
        new ObjectMapper().writeValue(WebApplication.FILE, allMessages);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                log.info("5 seconds past");
                send();
                Thread.sleep(5000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
