package com.example.demo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Repository
@AllArgsConstructor
public class FileRider {
    public void writer(String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dataFile", true));
            writer.write("\nRound, Team1, Team2, Score");
            writer.write("\n" + str + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
