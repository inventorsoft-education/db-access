package com.academy.task.service;

import com.academy.task.model.Email;
import com.academy.task.repository.EmailRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailService {

    EmailRepository emailRepository;

    public void addEmail(Email email) {
        emailRepository.add(email);
    }

    public List<Email> getEmailToSent() {
        return emailRepository.getEmailsToSend();
    }

    public List<Email> getAllEmails() {
        return emailRepository.getAll();
    }

    public Email getEmailById(Long id) {
        return emailRepository.getById(id);
    }

    public void deleteEmail(Long id) {
        emailRepository.deleteSent(id);
    }

    public Long getLargestId() {
        List<Email> emails = emailRepository.getAll();
        Optional<Email> resultList = emails.stream()
                .max(Comparator.comparing(Email::getId));

        return (resultList.isPresent() && emails.size() != 0) ? resultList.get().getId() : 0;
    }

    public void updateEmail(Long id, Email email) {
        emailRepository.update(id, email);
    }

}
