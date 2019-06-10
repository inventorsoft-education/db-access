package com.example.demo.model.entity;

import com.example.demo.model.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Column(name="cur_time")
    private long currentTime;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;
}
