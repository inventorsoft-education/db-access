package com.lelek.dbAccess.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageDto {
    private long id;
    private boolean sent;
    private String to;
    private String subject;
    private String text;
    private String date;
}
