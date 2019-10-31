package com.academy.task.controller;

import com.academy.task.model.Email;
import com.academy.task.service.EmailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/emails/")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailController {

    EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Email>> findAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }

    @GetMapping("{id}")
    public ResponseEntity<Email> findEmailById(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.getEmailById(id));
    }

    @PostMapping
    public ResponseEntity<String> createEmail(@Valid @RequestBody Email email) {
        emailService.addEmail(email);

        return ResponseEntity.ok().body("Email created successfully and will be sent at " + email.getDate().toLocalTime());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmail(@PathVariable("id") Long id, @Valid @RequestBody Email email) {
        emailService.updateEmail(id, email);

        return ResponseEntity.ok().body( "Email updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmail(@PathVariable("id") Long id) {
        emailService.deleteEmail(id);

        return ResponseEntity.noContent().build();
    }

}
