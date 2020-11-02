package com.paskar.email.application.repositiory.hibernate;

import com.paskar.email.application.model.Email;

import java.util.List;

public interface CustomRepositoryForProject {
    List<Email> findEmailsNearDeliveryDate();

    void save(Email email);

    Email findById(Long id);

    List<Email> findAll();

    void deleteById(Long id);
}
