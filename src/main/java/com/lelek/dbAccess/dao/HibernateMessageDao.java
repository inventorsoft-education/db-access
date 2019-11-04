package com.lelek.dbAccess.dao;

import com.lelek.dbAccess.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
public class HibernateMessageDao implements MessageDao {

    private MessageRepository repository;

    @Override
    public List<MessageDto> getMessages() {
        return repository.findAll();
    }

    @Override
    public void saveMessage(MessageDto messageDto) {
        repository.save(messageDto);
    }

    @Override
    public MessageDto getMessage(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void removeMessage(long id) {
        repository.delete(getMessage(id));
    }

    @Override
    public void updateMessage(long id, MessageDto updates) {
        MessageDto messageDto = repository.findById(id).get();
        messageDto.setTo(updates.getTo());
        messageDto.setId(id);
        messageDto.setSubject(updates.getSubject());
        messageDto.setText(updates.getText());
        messageDto.setDate(updates.getDate());
        messageDto.setSent(updates.isSent());
        repository.save(messageDto);

    }
}
