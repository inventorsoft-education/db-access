package com.academy.task.service;

import com.academy.task.exception.EmailNotFoundException;
import com.academy.task.model.Email;
import com.academy.task.repository.EmailRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailService {

    EmailRepository emailRepository;

    @Transactional
    public void addEmail(Email email) {
        emailRepository.save(email);
    }

    @Transactional(readOnly = true)
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Email getEmailById(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException("Email with id: " + id + " not founded"));
    }

    @Transactional(readOnly = true)
    public List<Email> getEmailToSent() {
        LocalDateTime currentDate = LocalDateTime.now();

        return getAllEmails().stream()
                .filter(e -> e.getDate().isBefore(currentDate))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateEmail(Long id, Email email) {
        Email emailFromDB = emailRepository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException("Email with id: " + id + " not founded"));

        emailFromDB.setRecipient(email.getRecipient());
        emailFromDB.setSubject(email.getSubject());
        emailFromDB.setBody(email.getBody());
        emailFromDB.setDate(email.getDate());

        emailRepository.save(emailFromDB);
    }

    @Transactional
    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }

}
