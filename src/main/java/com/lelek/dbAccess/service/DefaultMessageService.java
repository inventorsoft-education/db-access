package com.lelek.dbAccess.service;

import com.lelek.dbAccess.dao.MessageDao;
import com.lelek.dbAccess.dto.MessageDto;
import com.lelek.dbAccess.model.MySimpleMailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultMessageService implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MySimpleMailMessage mySimpleMailMessage;

    public List<MessageDto> getMessages() {
        return messageDao.getMessages().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void saveMessage(MessageDto messageDto) {
        messageDao.saveMessage(mapToSimpleMailMessage(messageDto));
    }

    @Override
    public MessageDto getMessage(long id) {
        return mapToDto(messageDao.getMessage(id));
    }

    @Override
    public void removeMessage(long id) {
        messageDao.removeMessage(id);
    }

    @Override
    public void updateMessage(long id, MessageDto updates) {
        messageDao.updateMessage(id, mapToSimpleMailMessage(updates));
    }

    private MySimpleMailMessage mapToSimpleMailMessage(MessageDto messageDto) {
        mySimpleMailMessage.setTo(messageDto.getTo());
        mySimpleMailMessage.setCc(messageDto.getTo());
        mySimpleMailMessage.setBcc(messageDto.getTo());
        mySimpleMailMessage.setSubject(messageDto.getSubject());
        mySimpleMailMessage.setText(messageDto.getText());
        try {
            mySimpleMailMessage.setSentDate(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(messageDto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mySimpleMailMessage.setSent(messageDto.isSent());
        return mySimpleMailMessage;
    }

    private MessageDto mapToDto(MySimpleMailMessage mySimpleMailMessage) {
        return MessageDto.builder()
                .id(mySimpleMailMessage.getId())
                .sent(mySimpleMailMessage.isSent())
                .to(Arrays.toString(mySimpleMailMessage.getTo()))
                .subject(mySimpleMailMessage.getSubject())
                .text(mySimpleMailMessage.getText())
                .date(Objects.requireNonNull(mySimpleMailMessage.getSentDate()).toString())
                .build();
    }

}
