package com.paskar.email.application.controller;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.hibernate.CustomRepositoryForProject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainController {

    CustomRepositoryForProject customRepositoryForProject;


    @GetMapping("/main")
    @ResponseStatus(HttpStatus.OK)
    public String mainPage() {
        return "home";
    }

    @GetMapping("/emails")
    @PreAuthorize("hasAnyAuthority('read','delete')")
    @ResponseStatus(HttpStatus.OK)
    public String showAllEmails(Model model) {
        model.addAttribute("emails", customRepositoryForProject.findAll());
        return "emails";
    }

    @GetMapping("/create-email")
    @PreAuthorize("hasAnyAuthority('write')")
    @ResponseStatus(HttpStatus.OK)
    public String createEmail(@ModelAttribute("email") Email email) {
        return "create_new_email";
    }

    @PostMapping("/create-email")
    @PreAuthorize("hasAnyAuthority('write')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewEmail(@ModelAttribute("email") Email email) {
        customRepositoryForProject.save(email);
        return "redirect:/main";
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/email/{id}")
    @PreAuthorize("hasAnyAuthority('read','delete')")
    @ResponseStatus(HttpStatus.OK)
    public String getEmailById(@PathVariable() Long id, Model model) {
        model.addAttribute("email", customRepositoryForProject.findById(id));
        return "show_email_by_id";
    }

    @DeleteMapping("/delete/email/{id}")
    @PreAuthorize("hasAnyAuthority('read','delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteByEmailById(@PathVariable Long id) {
        customRepositoryForProject.deleteById(id);
        return "redirect:/main";
    }
}
