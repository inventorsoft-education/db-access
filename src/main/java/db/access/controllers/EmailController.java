package db.access.controllers;
import db.access.model.Email;
import db.access.model.EmailRequest;
import db.access.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public List<Email> getAllEmails() throws SQLException {
        return emailService.getAllEmails();
    }

    @PostMapping(consumes = "application/json")
    public void addNewEmail(@RequestBody EmailRequest emailRequest) throws ParseException, SQLException {
        emailService.addNewEmail(emailRequest);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void updateDeliveryDate(@PathVariable long id, @RequestParam String date) throws ParseException, SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        Date date1 = dateFormat.parse(date);
        emailService.updateEmailDate(id, date1);
    }

    @DeleteMapping
    public void removePendingEmailDelivery() throws SQLException {
        emailService.removePendingEmails();
    }
}
