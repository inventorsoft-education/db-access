package com.sender.email.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name="emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @Column
    private String recipient;

    @Column
    private String subject;

    @Column
    private String body;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm", timezone="EET")
    private Date deliveryDate;

    private Boolean isSent;

    public Email() {
        recipient = "A1lexen30@gmail.com";
        subject = "TEST!";
        body = "TEST";
        deliveryDate = new Date();
        isSent = false;
    }

    @Override
    public String toString() {
        return "\nRecipient: " + recipient + "\n Subject: " + subject + "\n Body: " + body + "\n Delivery date: " + deliveryDate + "\n isSent: " + isSent;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(recipient.equals(((Email)obj).recipient)
                && subject.equals(((Email)obj).subject)
                && deliveryDate.equals(((Email)obj).deliveryDate)
                && body.equals(((Email)obj).body)) {
            return true;
        }
        return false;
    }

}
