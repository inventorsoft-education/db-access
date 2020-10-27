package co.inventorsoft.mailsecurity.repositories;

import co.inventorsoft.mailsecurity.models.Email;

import java.util.List;

public interface EmailDao {
    List<Email> findAll();
    void saveMail(Email email);
//    void saveAll(List<Email> emails) throws IOException;
    void delete(Email email);
//    void deleteAll(List<Email> emails) throws IOException;
    void update(Email email);
    List<Email> findById(int id);
    List<Email> mailsToSend();
}
