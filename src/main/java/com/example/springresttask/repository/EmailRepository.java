package com.example.springresttask.repository;

import com.example.springresttask.domain.Email;
import java.util.List;

public interface EmailRepository {

    List<Email> findAllByPendingEmail();

    void deletePendingEmail(Long id);


    Email save(Email email);

}
