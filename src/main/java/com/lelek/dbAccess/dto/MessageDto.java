package com.lelek.dbAccess.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private long id;

    private boolean sent;

    private String to;

    private String subject;

    private String text;

    private String date;
}
