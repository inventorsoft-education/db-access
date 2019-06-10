package com.example.demo.dao;

import com.example.demo.model.entity.Message;
import com.example.demo.model.enums.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository {
    void save(Message message);

    List<Message> getAllMessage();

    Message findById(Long id);

    void updateEmailTimeById(Long id, Long time);

    void updateEmailStatusById(Long id, Status status);

    void deleteById(Long id);
}

