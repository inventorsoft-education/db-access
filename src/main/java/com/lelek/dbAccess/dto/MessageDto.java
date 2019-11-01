package com.lelek.dbAccess.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MessageDto {
    private long id;
    private boolean sent;
    private String to;
    private String subject;
    private String text;
    private String date;
}
