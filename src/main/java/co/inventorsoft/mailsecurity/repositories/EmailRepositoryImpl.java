package co.inventorsoft.mailsecurity.repositories;

import co.inventorsoft.mailsecurity.models.Email;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmailRepositoryImpl{

    EmailRepository emailRepository;


    public void save(Email email) {
        emailRepository.save(email);
    }

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public void deleteById(Long id) {
        emailRepository.deleteById(id);
    }

    public List<Email> mailsToSend() {
        List<Email> emailList = findAll();
        return emailList.stream()
                .filter(email -> email.getDate().equals(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
