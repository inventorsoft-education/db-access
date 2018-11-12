package db.access.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emails")
public class Email{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column
    private String address;
    @Column
    private String subject;
    @Column
    private String text;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Email(){
    }

    public Email(Long id, String address, String subject, String text, Date date) {
        this.id = id;
        this.address = address;
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
