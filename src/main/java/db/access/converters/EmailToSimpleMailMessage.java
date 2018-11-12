package db.access.converters;

import db.access.model.Email;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class EmailToSimpleMailMessage implements Converter<Email, SimpleMailMessage> {

    @Override
    public SimpleMailMessage convert(Email email) {
        if (email == null){
            return null;
        }
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getAddress());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getText());
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(String.valueOf(email.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleMailMessage.setSentDate(date);
        return simpleMailMessage;
    }
}
