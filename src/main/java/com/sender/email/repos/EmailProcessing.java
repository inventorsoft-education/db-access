package com.sender.email.repos;

import com.sender.email.models.Email;

import java.util.Date;
import java.util.List;

public interface EmailProcessing {
    public List<Email> getAll();
    public List<Email> getUnsent();
    public void addNewEmail(Email email);
    public void delete(int index);
    public void changeDate(int id, Date newDate);
    public void changeStatus(int id);
}
