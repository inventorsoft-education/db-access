package com.example.demo.controller;

import com.example.demo.dao.MessageRepository;
import com.example.demo.model.entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/messages/")
public class MailSenderController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<Message> getMessages() {
        List<Message> sender = messageRepository.getAllMessage();
        return sender;
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable long id) {
        Message message = messageRepository.findById(id);
        return message;
    }

    @PostMapping
    public ResponseEntity addMessage(Message message) {
        messageRepository.save(message);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFutureDate(@PathVariable long id,
                                                 @RequestParam long date) {
        messageRepository.updateEmailTimeById(id, date);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable long id) {
        messageRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
