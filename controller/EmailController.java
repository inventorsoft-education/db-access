package com.example.demo.controller;

import com.example.demo.model.Email;
import com.example.demo.service.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController()
@RequestMapping("/Email")
public class EmailController {

    EmailServiceImplementation emailServiceImplementation;

    @Autowired
    public EmailController(EmailServiceImplementation emailServiceImplementation) {
        this.emailServiceImplementation = emailServiceImplementation;
    }

    @RequestMapping(value = "/activeEmails", method=GET)
    private ResponseEntity<ArrayList<Email>> showActiveEmails() {
       return ResponseEntity.ok(emailServiceImplementation.getActiveEmails());
    }

    @RequestMapping(value = "/newEmail", method=POST)
    private ResponseEntity<Email> addNew(@RequestBody Email newEmail) {
        return ResponseEntity.ok(emailServiceImplementation.addEmail(newEmail));
    }

    @DeleteMapping("/{id}")
    private void  deleteOne(@PathVariable Long id){
        emailServiceImplementation.deleteById(id);
    }

    @PutMapping(value="/newDateForOneEmail")
    private  void updateDeliveryDateForOneEmail  (@RequestParam Long id,
                                                  @DateTimeFormat(pattern = "dd-mm-yyyy HH:mm")
                                                  @RequestParam Date date){


        emailServiceImplementation.updateDeliveryDateForEmailById(id,date);

    }

    @RequestMapping(value = "/allEmails", method = GET)
    private ResponseEntity<ArrayList<Email>> getAllEmails(){
        return ResponseEntity.ok(emailServiceImplementation.getAllEmails());
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd-mm-yyyy HH:mm"), true, 10));
    }




}
