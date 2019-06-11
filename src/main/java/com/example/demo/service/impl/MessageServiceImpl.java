package com.example.demo.service.impl;

import com.example.demo.model.dto.MessageDto;
import com.example.demo.model.dto.MessageDtoFactory;
import com.example.demo.model.entity.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    @Transactional
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional
    public List<MessageDto> getAllMessagesDto() {
        return messageRepository.findAll().stream()
                .map(message -> MessageDtoFactory.create(message))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Message findById(Long id) {
        return messageRepository.getOne(id);
    }

    @Override
    @Transactional
    public void updateTimeById(long id, LocalDateTime time) {
        messageRepository.updateTimeById(id, time);
    }

    @Override
    @Transactional
    public void updateStatusById(long id, boolean status) {
        messageRepository.updateStatusById(id, status);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
