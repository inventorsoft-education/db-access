package com.lelek.dbAccess.dao;

import com.lelek.dbAccess.dto.MessageDto;

import java.util.List;

public interface MessageDao {

    List<MessageDto> getMessages();

    void saveMessage(MessageDto mySimpleMailMessage);

    MessageDto getMessage(long id);

    void removeMessage(long id);

    void updateMessage(long id, MessageDto updates);
}
