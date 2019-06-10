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
    private ArrayList<Email> emails=new ArrayList<>();
    private Email email=new Email();

    @Autowired
    public JDBCEmailRepository(Connection connection) {
        this.connection = connection;
    }


    public ArrayList<Email> getActive()  {
        emails.clear();

        try {

            statement = connection.createStatement();
            String selectActiveQuery="SELECT id_Email,recipient_Name,email_Subject,email_Body,delivery_Date,is_Sent FROM email WHERE is_Sent IS FALSE";
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
            String addEmailQuery = "INSERT INTO email(recipient_Name,email_Subject,email_Body,delivery_Date,is_Sent) VALUES (?,?,?,?,?)";
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
            String updateDeliveryDateForOneEmail="UPDATE Email SET delivery_Date=(?) WHERE id_Email=(?) ";
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
               String deleteEmailByID = "DELETE  FROM email WHERE id_Email=(?)";
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
            String selectAllQuery="SELECT id_Email,recipient_Name,email_Subject,email_Body,delivery_Date,is_Sent FROM email";
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
            String updateDeliveryDateForOneEmail="UPDATE Email SET is_Sent=TRUE WHERE recipient_Name=(?) AND email_Subject=(?) AND email_Body=(?)";
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
