package com.lelek.dbAccess.controller;

import com.lelek.dbAccess.dto.MessageDto;
import com.lelek.dbAccess.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/")
    public ResponseEntity<List<MessageDto>> getMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping(value = "/{id}")
    public MessageDto getMessage(@PathVariable long id){
        return messageService.getMessage(id);
    }

    @PostMapping(value = "/")
    public void saveMessage(@RequestBody MessageDto messageDto){
        messageService.saveMessage(messageDto);
    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable long id){
        messageService.removeMessage(id);
    }

    @PutMapping(value = "/{id}")
    public void updateMessage(@PathVariable long id, @RequestBody MessageDto messageDto){
        messageService.updateMessage(id, messageDto);
    }

}
