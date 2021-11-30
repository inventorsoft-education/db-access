package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;

@Repository
public class ClassForWorkingWithFile {
    public void writer(String str) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("dataFile", true))) {
            writer.write("\nRound, Team1, Team2, Score");
            writer.write("\n" + str + "\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
