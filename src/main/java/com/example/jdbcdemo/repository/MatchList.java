package com.example.jdbcdemo.repository;

import com.example.jdbcdemo.configuration.JdbcConfig;
import com.example.jdbcdemo.model.Match;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchList {

    List<Match> matchList = new ArrayList<>();
    JdbcConfig jdbcConfig;

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList() {
        this.matchList = jdbcConfig.matchesToList();
    }

}
