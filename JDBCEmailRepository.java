package com.example.demo.repository;

import com.example.demo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class JDBCEmailRepository implements EmailRepository {

    private Connection connection;
    private Statement statement=null;
    private ResultSet resultSet=null;
    private ArrayList<Email> emails=new ArrayList<Email>();
    private Email email=new Email();

    @Autowired
    public JDBCEmailRepository(Connection connection) {
        this.connection = connection;
    }


    public ArrayList<Email> getActive()  {
        emails.clear();

        try {

            statement = connection.createStatement();
            String selectActiveQuery="Select * from email where isSent=false";
            resultSet=statement.executeQuery(selectActiveQuery);

        while(resultSet.next()){

                email.setRecepientName(resultSet.getString(2));
                email.setEmailSubject(resultSet.getString(3));
                email.setEmailBody(resultSet.getString(4));
                email.setDeliveryDate(resultSet.getDate(5));
                email.setSended(resultSet.getBoolean(6));
                emails.add(email);

            }} catch (SQLException e) {
                e.printStackTrace();
            }
            emails.add(email);
        return emails;
    }

  public void  addNewEmail(Email email)  {

      try{
            String addEmailQuery = "INSERT INTO email(recipientName,emailSubject,emailBody,deliveryDate,isSent) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(addEmailQuery);
            preparedStatement.setString(1, email.getRecepientName());
            preparedStatement.setString(2,email.getEmailSubject());
            preparedStatement.setString(3,email.getEmailBody());
            Date sqlDate=new Date (email.getDeliveryDate().getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setBoolean(5, false);
            preparedStatement.executeUpdate();

      }catch (SQLException e){
          e.printStackTrace();
      }
  }

    @Override
    public void updateDeliveryDateForOneEmail(int id, Date date) {

        try{
            String updateDeliveryDateForOneEmail="update Email set deliveryDate=(?) where idEmail=(?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(updateDeliveryDateForOneEmail);
            preparedStatement.setDate(1,date);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOneEmail(int id)  {

        try {
               String deleteEmailByID = "delete  from email where idEmail=(?)";
               PreparedStatement preparedStatement = connection.prepareStatement(deleteEmailByID);
               preparedStatement.setInt(1, id);
               preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public ArrayList<Email> getAll()  {

        emails.clear();

        try {
            statement = connection.createStatement();
            String selectAllQuery="Select * from email";
            resultSet=statement.executeQuery(selectAllQuery);

            while(resultSet.next()){
                email.setRecepientName(resultSet.getString(2));
                email.setEmailSubject(resultSet.getString(3));
                email.setEmailBody(resultSet.getString(4));
                email.setDeliveryDate(resultSet.getDate(5));
                email.setSended(resultSet.getBoolean(6));
                emails.add(email);
                statement.close();

            }} catch (SQLException e) {
            e.printStackTrace();
        }
        emails.add(email);
        return emails;
    }

    public void updateStatus(Email email){

        try{
            String updateDeliveryDateForOneEmail="update Email set isSent=true where recipientName=(?) and emailSubject=(?) and emailBody=(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(updateDeliveryDateForOneEmail);
            preparedStatement.setString(1,email.getRecepientName());
            preparedStatement.setString(2,email.getEmailSubject());
            preparedStatement.setString(3,email.getEmailBody());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }



}
