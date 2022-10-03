package com.example.springresttask.service;

import com.example.springresttask.domain.Email;
import com.example.springresttask.repository.EmailRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepositoryImpl emailRepository;

    public List<Email> pendingEmailDeliveries() {
        return emailRepository.findAllByPendingEmail();
    }


    public Email createEmailDelivery(Email email) {
        return emailRepository.save(email);
    }


    public Email updateDeliveryDate(Email email) {

        if (email.isSent()) {
            throw new RuntimeException("you cannot change the date because the letter" +
                    " has already been sent");
        }
        return emailRepository.updateDeliveryDate(email);
    }

    public void removePendingEmail(Long id) {
        emailRepository.deletePendingEmail(id);
    }


}
