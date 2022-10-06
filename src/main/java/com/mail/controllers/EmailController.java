package com.mail.controllers;

import com.mail.dao.MessagesDao;
import com.mail.entities.TextMessage;
import com.mail.repository.DB;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/messages")
public class EmailController {

    MessagesDao mDao;
    DB db;

    @Autowired
    public EmailController(MessagesDao mDao, DB db) {
        this.mDao = mDao;
        this.db = db;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('read')")
    public List<TextMessage> getMessages() {
        //return mDao.readMessages();
        return db.getAllMessages();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public TextMessage getById(@PathVariable Integer id) {
        //return mDao.readMessage(id);
        return db.findTextMessage(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('write')")
    public void create(@RequestBody @Valid TextMessage textMessage) {
        db.addMessage(textMessage);
        //return mDao.addMessage(textMessage);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public void updateMessage(@PathVariable Integer id, @RequestBody TextMessage textMessage) {
        db.updateTextMessage(id, textMessage);
        //return mDao.update(id, textMessage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        db.deleteTextMessage(id);
        //mDao.deleteMessage(id);
    }
}
