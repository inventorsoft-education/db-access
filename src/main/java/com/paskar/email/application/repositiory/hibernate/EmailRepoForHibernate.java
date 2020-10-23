package com.paskar.email.application.repositiory.hibernate;

import com.paskar.email.application.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepoForHibernate extends JpaRepository<Email, Long> {

}
