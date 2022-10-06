package com.mail.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    WRITE("write"),
    READ("read");

    private final String permission;
}
