package com.example.demo.dao;

import com.example.demo.model.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository {
    void save(Message message);

    List<Message> getAllMessage();

    Message findById(Long id);

    void updateEmailTimeById(Long id, String timeStamp);

    void updateEmailStatusById(Long id, boolean status);

    void deleteById(Long id);
}

