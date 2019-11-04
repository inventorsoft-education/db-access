package com.lelek.dbAccess.service;

import com.lelek.dbAccess.dao.MessageDao;
import com.lelek.dbAccess.dto.MessageDto;
import com.lelek.dbAccess.model.MySimpleMailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultMessageService implements MessageService {

    private MessageDao messageDao;

    private MySimpleMailMessage mySimpleMailMessage;

    public List<MessageDto> getMessages() {
        return messageDao.getMessages();
    }

    public void saveMessage(MessageDto messageDto) {
        messageDao.saveMessage(messageDto);
    }

    @Override
    public MessageDto getMessage(long id) {
        return messageDao.getMessage(id);
    }

    @Override
    public void removeMessage(long id) {
        messageDao.removeMessage(id);
    }

    @Override
    public void updateMessage(long id, MessageDto updates) {
        messageDao.updateMessage(id, updates);
    }

}
