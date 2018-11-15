package com.demo.repository;

import com.demo.model.MailSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSenderRepository extends CrudRepository<MailSender, Integer>, JpaRepository<MailSender, Integer> {

}
