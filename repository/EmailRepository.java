package com.example.demo.repository;

import com.example.demo.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;


@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {

    ArrayList<Email> getAllByIsSentIsFalse() ;
    ArrayList<Email> findAll();
    void deleteById(Long id);
    Email saveAndFlush(Email email);
    @Query("UPDATE Email SET delivery_Date=date WHERE id=idForUpdate")
    Email updateDeliveryDateForEmailByID(@Param("idForUpdate")Long id,@Param("date")Date date);
    @Query("UPDATE Email SET isSent=TRUE WHERE id ")
    void updateIsSentStatusForEmailForByID(@Param("idForUpdate") Long id);



}
