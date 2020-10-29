package com.paskar.email.application.service;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.hibernate.CustomRepositoryForProject;
import com.paskar.email.application.repositiory.hibernate.EmailRepoForHibernate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmailRepositoryForHibernateIml implements CustomRepositoryForProject {

    EmailRepoForHibernate emailRepository;

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
}
