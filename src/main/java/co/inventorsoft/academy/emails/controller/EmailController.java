package co.inventorsoft.academy.emails.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.inventorsoft.academy.emails.EmailDAO;
import co.inventorsoft.academy.emails.EmailService;
import co.inventorsoft.academy.emails.VerySimpleMail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(value = "/emails")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EmailController {
	
	EmailDAO emailDAO;
	EmailService emailService;

    @GetMapping(produces = "application/json")
    public List<VerySimpleMail> getEmails() {
        return emailDAO.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public VerySimpleMail getEmailById(@PathVariable int id) {
        return emailDAO.get(id);
    }

    @PostMapping(consumes = "application/json")
    public void newEmail(@Valid @RequestBody VerySimpleMail email) {
    	emailDAO.add(email);
		emailService.sendNewEmail(email);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> updateDate(@PathVariable int id, 
    								@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
    	try {
        	emailDAO.update(id, date);
    		emailService.sendEmailNewDate(emailDAO.get(id));
        	return ResponseEntity.ok("{\"message\" : \"Send Date was changed\"}");
    	} catch(IndexOutOfBoundsException e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Index does not exist\"}");
    	}
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> removeEmail(@PathVariable int id) {
    	try {
    		VerySimpleMail email = emailDAO.get(id);
        	emailDAO.delete(id);
    		emailService.cancelEmail(email);
        	return ResponseEntity.ok("{\"message\" : \"Email was removed\"}");
    	} catch(IndexOutOfBoundsException e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Index does not exist\"}");
    	}
    }

    @DeleteMapping()
    public void clear() {
    	emailDAO.clear();
		emailService.clear();
    }

}
