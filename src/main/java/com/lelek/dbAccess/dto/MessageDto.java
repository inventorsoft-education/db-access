package com.lelek.dbAccess.dto;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
