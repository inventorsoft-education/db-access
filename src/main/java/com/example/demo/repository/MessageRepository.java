package com.example.demo.repository;

import com.example.demo.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Message m set m.timeStamp=?2 where m.id=?1")
    void updateTimeById(long id, LocalDateTime time);

    @Modifying
    @Transactional
    @Query(value = "update Message m set m.status=?2 where m.id=?1")
    void updateStatusById(long id, boolean status);
}
