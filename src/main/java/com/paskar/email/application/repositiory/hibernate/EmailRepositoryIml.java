package com.paskar.email.application.repositiory.hibernate;

import com.paskar.email.application.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailRepositoryIml {

    private final EmailRepoForHibernate emailRepository;

    @Autowired
    public EmailRepositoryIml(EmailRepoForHibernate emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void save(Email email) {
        emailRepository.save(email);
    }

    public Email findById(Long id) {
        return emailRepository.getOne(id);
    }

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public void deleteById(Long id) {
        emailRepository.deleteById(id);
    }

    public List<Email> findEmailsNearDeliveryDate() {
        LocalDate time = LocalDate.now();
        List<Email> emailList = findAll();
        List<Email> result = new ArrayList<>();

        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                result.add(email);
            }
        }
        return result;
    }

    public void deleteByEmailByDate(LocalDate time){
        List<Email> emails = emailRepository.findAll();
        emails.removeIf(email -> email.getDate().equals(time));
    }
}
