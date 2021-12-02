package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Repository
@AllArgsConstructor
public class FileService {
    public void writer(String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dataFile.txt", true));
            writer.write("\nRound, Team1, Team2, Score");
            writer.write("\n" + str + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
