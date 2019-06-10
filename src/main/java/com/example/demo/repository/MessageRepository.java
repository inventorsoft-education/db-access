package com.example.demo.repository;

import com.example.demo.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findById(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE messages SET future_second=?2 WHERE id=?1",
            nativeQuery = true)
    void updateTimeById(long id, long future_second);

    @Transactional
    @Modifying
    @Query(value = "UPDATE messages SET status=?2 WHERE id=?1",
            nativeQuery = true)
    void updateStatusById(long id, String status);
}
