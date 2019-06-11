package com.example.homework6.databases;

import com.example.homework6.EmailItem;
import java.util.ArrayList;

public interface EmailRepo {

    void save (EmailItem emailItem);

    ArrayList<EmailItem> findAll();

    EmailItem findById(Integer emailId);

    void delete(EmailItem emailItem);
}
