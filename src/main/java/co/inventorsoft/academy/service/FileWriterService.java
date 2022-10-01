package co.inventorsoft.academy.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;

@Service
@NoArgsConstructor
@Slf4j
public class FileWriterService {
    /**
     * This method write to file String value of str parameter
     *
     * @param str String value
     */
    public void write(String str) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("data.csv", true))) {
            writer.write(str + "\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
