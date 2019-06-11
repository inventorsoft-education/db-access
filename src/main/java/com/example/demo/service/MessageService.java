package com.example.demo.service;

import com.example.demo.model.dto.MessageDto;
import com.example.demo.model.entity.Message;

import java.time.LocalDateTime;
import java.util.List;


public interface MessageService {
    void save(Message message);

    List<Message> getAllMessages();

    List<MessageDto> getAllMessagesDto();

    Message findById(Long id);

    void updateTimeById(long id, LocalDateTime time);

    void updateStatusById(long id, boolean status);

    void deleteById(Long id);
}

