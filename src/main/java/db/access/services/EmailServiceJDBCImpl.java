package db.access.services;
import db.access.DatabaseConnection;
import db.access.model.Email;
import db.access.model.EmailRequest;
import db.access.converters.EmailToSimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.sql.*;
import org.springframework.scheduling.TaskScheduler;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Profile("JDBC")
public class EmailServiceJDBCImpl implements EmailService {

    private DatabaseConnection databaseConnection;
    private TaskScheduler taskScheduler;
    private JavaMailSender javaMailSender;
    private EmailToSimpleMailMessage emailToSimpleMailMessage;

    private String selectAllQuery = "select id, address, text, subject, date from emails";
    private String addEmailQuery = "INSERT INTO emails(address, subject, text, date ) VALUES (?,?,?,?)";
    private String updateDateQuery = "UPDATE emails SET date = ? WHERE id = ?";
    private String deletePendingEmailQuery = " TRUNCATE emails";
    private String deleteEmailByIdQuery = "DELETE FROM emails WHERE id = ?";

    @Autowired
    public EmailServiceJDBCImpl(DatabaseConnection databaseConnection,
                                TaskScheduler taskScheduler,
                                JavaMailSender javaMailSender,
                                EmailToSimpleMailMessage emailToSimpleMailMessage) {
        this.databaseConnection = databaseConnection;
        this.taskScheduler = taskScheduler;
        this.javaMailSender = javaMailSender;
        this.emailToSimpleMailMessage = emailToSimpleMailMessage;
    }

    public List<Email> getAllEmails() throws SQLException {
        Statement statement = databaseConnection.createConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(selectAllQuery);
        List<Email> emails = new ArrayList<>();
        while (resultSet.next()) {
            Email email = new Email();
            email.setId(resultSet.getLong("id"));
            email.setAddress(resultSet.getString("address"));
            email.setSubject(resultSet.getString("subject"));
            email.setText(resultSet.getString("text"));
            email.setDate(resultSet.getTimestamp("date"));
            emails.add(email);
        }
        return emails;
    }

    @Override
    public void addNewEmail(EmailRequest emailRequest) throws SQLException, ParseException {
        PreparedStatement preparedStatement = databaseConnection.createConnection().
                prepareStatement(addEmailQuery);
        preparedStatement.setString(1, emailRequest.getAddress());
        preparedStatement.setString(2, emailRequest.getSubject());
        preparedStatement.setString(3, emailRequest.getText());
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        Date date = dateFormat.parse(emailRequest.getDate());
        preparedStatement.setTimestamp(4,new java.sql.Timestamp(date.getTime()));
        preparedStatement.execute();
        preparedStatement.close();
        sendScheduledEmail();
    }

    @Override
    public void updateEmailDate(long id, Date date) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.createConnection().
                prepareStatement(updateDateQuery);
        preparedStatement.setTimestamp(1, new java.sql.Timestamp(date.getTime()));
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void removePendingEmails() throws SQLException {
        Statement statement = databaseConnection.createConnection().createStatement();
        statement.executeUpdate(deletePendingEmailQuery);
        statement.close();
    }

    public void sendScheduledEmail() throws SQLException {
        List<Email> simpleMailMessages = getAllEmails();
        for(Email email : simpleMailMessages){
            taskScheduler.schedule(()->{
                javaMailSender.send(emailToSimpleMailMessage.convert(email));
                try {
                    clearEmail(email.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            },emailToSimpleMailMessage.convert(email).getSentDate());
        }
    }

    public void clearEmail(long id) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.createConnection().prepareStatement(deleteEmailByIdQuery);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
