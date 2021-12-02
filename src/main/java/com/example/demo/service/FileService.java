package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;

@Service
public class FileService {
    public void writer(String str) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("dataFile", true))) {
            writer.write("\nRound, Team1, Team2, Score");
            writer.write("\n" + str + "\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
