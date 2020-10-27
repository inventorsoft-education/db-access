package com.paskar.email.application.controller;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.EmailRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class MainController {

    private final EmailRepository repository;

    public MainController(@Qualifier("emailRepositoryJdbcImpl") EmailRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/main")
    @ResponseStatus(HttpStatus.OK)
    public String mainPage() {
        return "home";
    }

    @GetMapping("/emails")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    @ResponseStatus(HttpStatus.OK)
    public String showAllEmails(Model model) throws IOException {
        model.addAttribute("emails", repository.findAll());
        return "emails";
    }

    @GetMapping("/create-email")
    @PreAuthorize("hasAnyAuthority('write')")
    @ResponseStatus(HttpStatus.OK)
    public String createEmail() {
        return "create_new_email";
    }

    @PostMapping("/create-email")
    @PreAuthorize("hasAnyAuthority('write')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewEmail(@RequestParam String recipient,
                                 @RequestParam String subject,
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate dateTime,
                                 @RequestParam String body) {
        repository.save(new Email(recipient, subject, body, dateTime));
        return "redirect:/main";
    }

    @DeleteMapping("/delete/email/{time}")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmailByDate(@PathVariable LocalDate time) {
        repository.deleteByEmailByDate(time);
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/email/{id}")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    @ResponseStatus(HttpStatus.OK)
    public String getEmailById(@PathVariable() int id, Model model) {
        model.addAttribute("email", repository.getById(id));
        return "show_email_by_id";
    }

    @DeleteMapping("/delete/email/{id}")
    @PreAuthorize("hasAnyAuthority('delete/read')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteByEmailById(@PathVariable int id) {
       repository.deleteById(id);
       return "redirect:/main";
    }
}
