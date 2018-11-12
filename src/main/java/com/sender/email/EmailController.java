package com.sender.email;

import com.sender.email.repos.EmailDbProcessing;
import com.sender.email.repos.EmailProcessing;
import com.sender.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/emails")
@AllArgsConstructor
public class EmailController {
    EmailDbProcessing processing;
    EmailService emailService;

    @GetMapping
    public List<Email> getEmails() {
        return processing.getAll();
    }

    @PostMapping(consumes = "application/json")
    public void newEmail(@RequestBody List<Email> email){
        processing.addNewEmail(email);
        emailService.sendAll();
    }

    @PutMapping(value = "/{id}/date", produces = "application/json")
    public ResponseEntity<String> updateDate(@PathVariable(value="id") int id,@RequestParam(value="date") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date newDate){
        int last_id = processing.getAll().get(processing.getAll().size()-1).getId();
        if (id > last_id) {
            return ResponseEntity.status(404).body("404\nIndex out of range");
        }
        processing.changeDate(id, newDate);
        return ResponseEntity.ok(String.format("Delivery date of email with id: %s has been changed!", id));
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteEmail(@RequestParam(value="id") int id) {
        int last_id = processing.getAll().get(processing.getAll().size()-1).getId();
        if (id > last_id) {
            return ResponseEntity.status(404).body("404\nIndex out of range");
        }
        processing.delete(id);
        return ResponseEntity.ok(String.format("Email with id: %s has been deleted!", id));
    }

}
