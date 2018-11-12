package db.access.services;
import db.access.model.Email;
import db.access.model.EmailRequest;
import db.access.converters.EmailToSimpleMailMessage;
import db.access.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Profile("Hibernate")
public class EmailServiceHibernateImpl implements EmailService {

    private EmailRepository emailRepository;
    private TaskScheduler taskScheduler;
    private JavaMailSender javaMailSender;
    private EmailToSimpleMailMessage emailToSimpleMailMessage;
    @Autowired
    public EmailServiceHibernateImpl(EmailRepository emailRepository,
                                     TaskScheduler taskScheduler,
                                     JavaMailSender javaMailSender,
                                     EmailToSimpleMailMessage emailToSimpleMailMessage) {
        this.emailRepository = emailRepository;
        this.taskScheduler = taskScheduler;
        this.javaMailSender = javaMailSender;
        this.emailToSimpleMailMessage = emailToSimpleMailMessage;
    }

    @Override
    public List<Email> getAllEmails() {
        List<Email> emails = new ArrayList<>();
        emailRepository.findAll().iterator().forEachRemaining(emails::add);
        return emails;
    }

    @Override
    public void addNewEmail(EmailRequest emailRequest) throws ParseException {
        Email email = new Email();
        email.setAddress(emailRequest.getAddress());
        email.setSubject(emailRequest.getSubject());
        email.setText(emailRequest.getText());
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        Date date = dateFormat.parse(emailRequest.getDate());
        email.setDate(date);
        emailRepository.save(email);
        sendScheduledEmail();
    }

    @Override
    public void updateEmailDate(long id, Date date) {
        Optional<Email> emailOptional = emailRepository.findById(id);
        if(!emailOptional.isPresent()){
            throw new RuntimeException("Email is not found");
        }
        Email email = emailOptional.get();
        email.setDate(date);
        emailRepository.save(email);
    }

    @Override
    public void removePendingEmails(){
        emailRepository.deleteAll();
    }

    public void sendScheduledEmail(){
        List<Email> emails = getAllEmails();
        for(Email email : emails){
            taskScheduler.schedule(()->{
                javaMailSender.send(emailToSimpleMailMessage.convert(email));
                clearEmail(email.getId());
            },emailToSimpleMailMessage.convert(email).getSentDate());
        }
    }

    public void clearEmail(long id){
        emailRepository.deleteById(id);
    }
}
