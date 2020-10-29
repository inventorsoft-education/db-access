package co.inventorsoft.mailsecurity.controllers;

import co.inventorsoft.mailsecurity.models.Email;
import co.inventorsoft.mailsecurity.repositories.EmailRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;

@Controller
public class MailController {

    final
    EmailRepositoryImpl emailRepository;

    public MailController(EmailRepositoryImpl emailRepository) {
        this.emailRepository = emailRepository;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getMailPage(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        return "mail";
    }

    @GetMapping("/mail")
    @ResponseStatus(HttpStatus.OK)
    public String createMail(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        return "mail";
    }

    @PostMapping("/mail")
    @ResponseStatus(HttpStatus.OK)
    public String saveMail(@ModelAttribute("email") Email email) {
        emailRepository.save(email);
        return "redirect:/";
    }

    @DeleteMapping("mail/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteMail(@PathVariable Long id) {
        emailRepository.deleteById(id);
        return "redirect:/";
    }
}
