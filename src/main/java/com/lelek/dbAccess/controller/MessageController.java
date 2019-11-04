package com.lelek.dbAccess.controller;

import com.lelek.dbAccess.dto.MessageDto;
import com.lelek.dbAccess.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @GetMapping(value = "/")
    public ResponseEntity<List<MessageDto>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping(value = "/{id}")
    public MessageDto getMessage(@PathVariable long id) {
        return messageService.getMessage(id);
    }

    @PostMapping(value = "/")
    public void saveMessage(@RequestBody MessageDto messageDto) {
        messageService.saveMessage(messageDto);
    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable long id) {
        messageService.removeMessage(id);
    }

    @PutMapping(value = "/{id}")
    public void updateMessage(@PathVariable long id, @RequestBody MessageDto messageDto) {
        messageService.updateMessage(id, messageDto);
    }

}
