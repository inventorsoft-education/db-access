package com.example.demo.repository;

import com.example.demo.Email;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmailRepository {

    public ArrayList<Email> getActive() ;
    public void addNewEmail(Email email);
    public void updateDeliveryDateForOneEmail(int id, Date date);
    public void deleteOneEmail(int id);


}
