package com.example.demo.service.impl;

import com.example.demo.model.dto.MessageDto;
import com.example.demo.model.dto.MessageDtoFactory;
import com.example.demo.model.entity.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        message.setCurrentTime(new Date().getTime());

        messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessage() {
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    @Override
    public List<MessageDto> getAllMessageDto() {
        List<Message> messages = messageRepository.findAll();
        List<MessageDto> messageDtoList = new ArrayList<>();
        messages.forEach(message ->
            messageDtoList.add(MessageDtoFactory.create(message)));
        return messageDtoList;
    }

    @Override
    public Message findById(Long id) {
        Message message = messageRepository.findById(id).get();
        return message;
    }

    @Override
    public void updateTimeById(long id, long second) {
        messageRepository.updateTimeById(id, second);
    }

    @Override
    public void updateStatusById(long id, String status) {
        messageRepository.updateStatusById(id, status);
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
