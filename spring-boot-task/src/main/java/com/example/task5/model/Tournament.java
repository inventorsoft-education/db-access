package com.example.task5.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
public class Tournament {

    Integer id ;
    String name;

    public Tournament(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
