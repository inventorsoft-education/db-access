package com.example.demo.service;

import com.example.demo.model.dto.MessageDto;
import com.example.demo.model.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    void save(Message message);

    List<Message> getAllMessage();

    List<MessageDto> getAllMessageDto();

    Message findById(Long id);

    void updateTimeById(long id, long second);

    void updateStatusById(long id, String status);

    void deleteById(Long id);
}

