package com.paskar.email.application.controller;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.hibernate.EmailRepoForHibernate;
import com.paskar.email.application.repositiory.hibernate.EmailRepositoryIml;
import com.paskar.email.application.repositiory.jdbc.EmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    private final EmailRepositoryIml emailRepository;

    @Autowired
    public MainController(EmailRepositoryIml emailRepository) {
        this.emailRepository = emailRepository;
    }


    @GetMapping("/main")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/emails")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    public String showAllEmails(Model model) {
        model.addAttribute("emails", emailRepository.findAll());
        return "emails";
    }

    @GetMapping("/emails-create")
    @PreAuthorize("hasAnyAuthority('write')")
    public String createEmail(Model model) {
        return "create_new_email";
    }

    @PostMapping("/emails-create")
    @PreAuthorize("hasAnyAuthority('write')")
    public String createNewEmail(@RequestParam String recipient,
                                 @RequestParam String subject,
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                 @RequestParam LocalDate dateTime,
                                 @RequestParam String body,
                                 Model model) {
        emailRepository.save(new Email(recipient, subject, body, dateTime));
        return "redirect:/main";
    }

    @DeleteMapping("/delete/email/{time}")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    public void deleteByEmailByDate(@PathVariable LocalDate time) {//this controller just for example, the html page was not created
        emailRepository.deleteByEmailByDate(time);
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/email/{id}")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    public String getEmailById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("email", emailRepository.findById(id));
        return "show_email_by_id";
    }

    @GetMapping("/delete/email/{id}")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    public String deleteByEmailById(@PathVariable Long id) {
        emailRepository.deleteById(id);
       return "redirect:/main";
    }

}
