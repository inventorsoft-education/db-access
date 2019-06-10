package com.example.demo.controller;

import com.example.demo.model.dto.MessageDto;
import com.example.demo.model.entity.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
@RequestMapping("/messages/")
public class MailSenderController {

    @Autowired
    private MessageService messageService;

    @RequestMapping
    public List<MessageDto> getMessages() {
        List<MessageDto> sender = messageService.getAllMessagesDto();
        return sender;
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable long id) {
        Message message = messageService.findById(id);
        return message;
    }

    @PostMapping
    public ResponseEntity addMessage(@Validated Message message) {
        messageService.save(message);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFutureDate(@PathVariable long id,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime time) {
        messageService.updateTimeById(id, time);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
