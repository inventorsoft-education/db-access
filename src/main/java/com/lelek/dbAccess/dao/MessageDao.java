package com.lelek.dbAccess.dao;

import com.lelek.dbAccess.model.MySimpleMailMessage;

import java.util.List;

public interface MessageDao {

    List<MySimpleMailMessage> getMessages();

    void saveMessage(MySimpleMailMessage mySimpleMailMessage);

    MySimpleMailMessage getMessage(long id);

    void removeMessage(long id);

    void updateMessage(long id, MySimpleMailMessage updates);
}
