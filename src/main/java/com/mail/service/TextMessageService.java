package com.mail.service;

import com.mail.entities.TextMessage;
import com.mail.repository.TextMessageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TextMessageService {

    final TextMessageRepository tr;

    @Transactional
    public TextMessage saveTextMessage(TextMessage tm) {
        return tr.save(tm);
    }

    @Transactional
    public List<TextMessage> getAll() {
        return tr.findAll();
    }

    @Transactional
    public TextMessage findById(int id) {
        return tr.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(int id) {
        tr.deleteById(id);
    }

    @Transactional
    public void update(int id, TextMessage textm){
        TextMessage tm = findById(id);
        tm.setMessage(textm.getMessage());
        tm.setTitle(textm.getTitle());
        tm.setFrom_who(textm.getFrom_who());
        tm.setTo_whom(textm.getTo_whom());
        tr.save(tm);
    }
}


