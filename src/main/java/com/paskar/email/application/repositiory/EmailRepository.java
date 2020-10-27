package com.paskar.email.application.repositiory;

import com.paskar.email.application.model.Email;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface EmailRepository {
    List<Email> findAll() throws IOException;

    void save(Email email) ;

    Email getById(int id);

    void update(Email email);

    void deleteById(int id);

    List<Email> findEmailsNearDeliveryDate() throws IOException;

    void deleteByEmailByDate(LocalDate time);

    void deletingMassagesThatWereSent() throws IOException;

    void save(List<Email> email) throws IOException;
}
