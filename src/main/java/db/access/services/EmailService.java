package db.access.services;
import db.access.model.Email;
import db.access.model.EmailRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface EmailService {

    List<Email> getAllEmails() throws SQLException;
    void addNewEmail(EmailRequest emailRequest) throws SQLException, ParseException;
    void updateEmailDate(long id, Date date) throws SQLException;
    void removePendingEmails() throws SQLException;
}
