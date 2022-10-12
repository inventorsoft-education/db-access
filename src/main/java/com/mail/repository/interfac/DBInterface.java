package com.mail.repository.interfac;

import com.mail.entities.TextMessage;

import java.util.List;

public interface DBInterface {

    void addMessage(TextMessage message);

    List<TextMessage> getAllMessages();

    void updateTextMessage(int id, TextMessage textMessage);

    void deleteTextMessage(int id);

    TextMessage findTextMessage(int id);

}
