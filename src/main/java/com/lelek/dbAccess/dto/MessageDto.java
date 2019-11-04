package com.lelek.dbAccess.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;


@Data
@AllArgsConstructor
@Entity
@Table(name = "mail_message")
public class MessageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long id;

    @Column
    private boolean sent;

    @Column(name = "recipient")
    private String to;

    @Column
    private String subject;

    @Column
    private String text;

    @Column(name = "send_date")
    private String date;
}
