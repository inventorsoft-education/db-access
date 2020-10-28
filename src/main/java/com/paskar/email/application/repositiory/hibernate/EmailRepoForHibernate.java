package com.paskar.email.application.repositiory.hibernate;

import com.paskar.email.application.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmailRepoForHibernate extends JpaRepository<Email, Long> {
   default  List<Email> findEmailsNearDeliveryDate(){
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
