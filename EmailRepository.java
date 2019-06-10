package com.example.demo.repository;

import com.example.demo.Email;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmailRepository {

     ArrayList<Email> getActive() ;
     void addNewEmail(Email email);
     void updateDeliveryDateForOneEmail(int id, Date date);
     void deleteOneEmail(int id);


}
