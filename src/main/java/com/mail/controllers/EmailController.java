package com.mail.controllers;

import com.mail.entities.TextMessage;
import com.mail.service.TextMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class EmailController {

    final TextMessageService ts;

    @GetMapping()
    public List<TextMessage> getMessages() {
        return ts.getAll();
    }

    @GetMapping("/{id}")
    public TextMessage getById(@PathVariable Integer id) {
        return ts.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TextMessage textMessage) {
        ts.saveTextMessage(textMessage);
    }

    @PutMapping("/{id}")
    public void updateMessage(@PathVariable Integer id, @RequestBody TextMessage textMessage) {
        ts.update(id, textMessage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        ts.deleteById(id);
    }
}
