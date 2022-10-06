package com.mail.dao;

import com.mail.entities.TextMessage;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessagesDao {
    List<TextMessage> mDao = new ArrayList<>();

    {
        mDao.add(new TextMessage("Job", "oleg.buk@gmail.com", "ira.cat@yahoo.com", "I found a new job!"));
        mDao.add(new TextMessage("Car", "ivan.dub@gmail.com", "denis.bratuh@yahoo.com", "I bought a new car!"));
    }

    public Integer addMessage(TextMessage m) {
        mDao.add(m);
        return m.getId();
    }

    public void deleteMessage(int id) {
        mDao.remove(id);
    }

    private TextMessage findMessage(int id) {
        return mDao.stream()
                .filter(textMessage -> textMessage.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Message not found!"));

    }

    public TextMessage update(int id, TextMessage text) {
        findMessage(id).update(text);
        return text;
    }

    public TextMessage readMessage(int id) {
        return findMessage(id);
    }

    public List<TextMessage> readMessages() {
        return mDao;
    }
}


