package com.lelek.dbAccess.service;

import com.lelek.dbAccess.dto.MessageDto;

import java.util.List;

public interface MessageService {

    List<MessageDto> getMessages();

    void saveMessage(MessageDto messageDto);

    MessageDto getMessage(long id);

    void removeMessage(long id);

    void updateMessage(long id, MessageDto updates);

}
