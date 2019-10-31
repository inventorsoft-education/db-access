package com.lelek.dbAccess.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.dbAccess.WebApplication;
import com.lelek.dbAccess.model.MySimpleMailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DefaultMessageDao implements MessageDao {

    @Override
    public List<MySimpleMailMessage> getMessages() {
        try {
            if (!(new Scanner(WebApplication.FILE).hasNext())) {
                return new ArrayList<>();
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                List<MySimpleMailMessage> mailMessageList;
                mailMessageList = objectMapper
                        .readValue(WebApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                        });
                return mailMessageList;
            }
        } catch (IOException e) {
            log.info("Exception: " + e);
            return new ArrayList<>();
        }
    }

    @Override
    public void saveMessage(MySimpleMailMessage mySimpleMailMessage) {
        try {
            mySimpleMailMessage.setId(generateId());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
            List<MySimpleMailMessage> messageList = new ArrayList<>();
            if (mySimpleMailMessage.getId() != 1) {
                messageList = objectMapper
                        .readValue(WebApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                        });
            }
            messageList.add(mySimpleMailMessage);
            objectMapper.writeValue(WebApplication.FILE, messageList);
        } catch (IOException e) {
            log.info("Exception " + e);
        }
    }

    @Override
    public MySimpleMailMessage getMessage(long id) {
        return getMessages().stream()
                .filter(message -> message.getId() == id)
                .findAny()
                .orElse(new MySimpleMailMessage());
    }

    @Override
    public void removeMessage(long id) {
        List<MySimpleMailMessage> filteredList = getMessages().stream()
                .filter(message -> message.getId() != id)
                .collect(Collectors.toList());
        try {
            new ObjectMapper().writeValue(WebApplication.FILE, filteredList);
        } catch (IOException e) {
            log.info("Exception " + e);
        }
    }

    @Override
    public void updateMessage(long id, MySimpleMailMessage updates) {
        removeMessage(id);
        try {
            updates.setId(id);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
            List<MySimpleMailMessage> messageList = objectMapper
                    .readValue(WebApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {});
            messageList.add(updates);
            objectMapper.writeValue(WebApplication.FILE, messageList);
        } catch (IOException e) {
            log.info("Exception " + e);
        }
    }

    private static long generateId() throws IOException {
        if (!(new Scanner(WebApplication.FILE).hasNext())) {
            return 1L;
        } else {
            List<MySimpleMailMessage> mailMessageList;
            mailMessageList = new ObjectMapper()
                    .configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .readValue(WebApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                    });
            return mailMessageList.size() + 1;
        }
    }

}
