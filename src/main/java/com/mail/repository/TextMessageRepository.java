package com.mail.repository;

import com.mail.entities.TextMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextMessageRepository extends JpaRepository<TextMessage, Integer> {
}
