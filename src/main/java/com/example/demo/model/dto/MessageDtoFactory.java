package com.example.demo.model.dto;

import com.example.demo.model.entity.Message;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class MessageDtoFactory {

    public static MessageDto create(Message message) {
        return new MessageDto(
                message.getId(),
                message.getSubject(),
                message.getEmailTo(),
                message.getEmailText(),
                message.getTimeStamp(),
                message.isStatus()
        );
    }
}
