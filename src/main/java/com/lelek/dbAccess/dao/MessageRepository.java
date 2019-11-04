package com.lelek.dbAccess.dao;

import com.lelek.dbAccess.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageDto, Long> {}
