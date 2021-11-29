package com.example.jdbcdemo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {

    String name;
    String capitan;
    String coach;

    @Override
    public String toString() {
        return name + ", capitan - " + capitan + ", coach - " + coach;
    }
}
