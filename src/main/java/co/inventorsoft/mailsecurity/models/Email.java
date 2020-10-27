package co.inventorsoft.mailsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {
    private int id;
    private String recipient;
    private String subject;
    private String body;
    private LocalDateTime date;

    public Email(String recipient, String subject, String body, LocalDateTime date) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.date = date;
    }
}
