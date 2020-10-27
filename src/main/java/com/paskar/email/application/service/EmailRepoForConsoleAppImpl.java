package com.paskar.email.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.EmailRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailRepoForConsoleAppImpl implements EmailRepository {
    static String baseFile = "emailList.json";

    ObjectMapper mapper;

    @Override
    public void save(List<Email> email) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(baseFile, true)))) {
            mapper.writeValue(out, email);
        }
    }

    @Override
    public List<Email> findAll() throws IOException {
        return mapper.readValue(new File(baseFile), new TypeReference<>() {
        });
    }

    @Override
    public List<Email> findEmailsNearDeliveryDate() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        List<Email> result = new ArrayList<>();

        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                result.add(email);
            }
        }
        return result;
    }

    @Override
    public void deletingMassagesThatWereSent() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        List<Email> resultList = new ArrayList<>();

        for (Email email : emailList) {
            if (!time.equals(email.getDate())) {
                resultList.add(email);
            }
        }
        mapper.writeValue(new File(baseFile), resultList);
    }

    @Override
    public void save(Email email) {

    }

    @Override
    public Email getById(int id) {
        return null;
    }

    @Override
    public void update(Email email) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByEmailByDate(LocalDate time) {

    }
}
