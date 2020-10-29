package co.inventorsoft.mailsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emails")
@Data
public class Email {
    @Id
    @GeneratedValue
    private Long id;
    private String recipient;
    private String subject;
    private String body;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;
}
