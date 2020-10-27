package co.inventorsoft.mailsecurity.controllers;

import co.inventorsoft.mailsecurity.models.Email;
import co.inventorsoft.mailsecurity.repositories.EmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class MailController {

    @Autowired
    EmailDao emailDao;

    @GetMapping("/")
    public String getMailPage(ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        return "mail";
    }

    @GetMapping("/mail")
    public String createMail(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        return "mailForm";
    }

    @PostMapping("/mail")
    public String saveMail(@RequestParam String recipient, @RequestParam String subject,
                             @RequestParam String body, @RequestParam LocalDateTime date, Model model) {

        emailDao.saveMail(new Email(recipient, subject, body, date));
        return "redirect:/mail";
    }
}
