package com.example.demo;

import java.io.Serializable;
import java.util.Date;


public class Email implements Serializable {
    private String recipientName;
    private String emailSubject;
    private String emailBody;
    private Date deliveryDate;
    private boolean isSent;

    public void setRecepientName(String recepientName) {
        this.recipientName = recepientName;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public void setDeliveryDate(Date delieveryDate) {
        this.deliveryDate = delieveryDate;
    }

    public void setSended(boolean isSended) {
        this.isSent = isSended;
    }

    public String getRecepientName() {
        return recipientName;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public boolean getSended() {
        return isSent;
    }
    @Override

    public String toString() {
        return this.getEmailBody() + " " + this.getRecepientName() + " " + this.getEmailSubject() + " " + this.getDeliveryDate() + " " + this.getSended();
    }

}
