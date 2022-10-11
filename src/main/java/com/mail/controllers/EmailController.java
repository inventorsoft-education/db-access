package com.mail.controllers;

import com.mail.entities.TextMessage;
import com.mail.repository.DB;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class EmailController {

    final DB db;

    @GetMapping()
    public List<TextMessage> getMessages() {
        return db.getAllMessages();
    }

    @GetMapping("/{id}")
    public TextMessage getById(@PathVariable Integer id) {
        return db.findTextMessage(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid TextMessage textMessage) {
        db.addMessage(textMessage);
    }

    @PutMapping("/{id}")
    public void updateMessage(@PathVariable Integer id, @RequestBody TextMessage textMessage) {
        db.updateTextMessage(id, textMessage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        db.deleteTextMessage(id);
    }
}
