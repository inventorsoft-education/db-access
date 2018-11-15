package com.sender.email;

import com.sender.email.models.Email;
import com.sender.email.repos.EmailProcessing;
import com.sender.email.service.EmailSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/emails")
@AllArgsConstructor
@Api(value="Email sender", description = "Operations relating to emails processing")
public class EmailController {
    EmailProcessing processing;
    EmailSender emailSender;

    @GetMapping
    @ApiOperation(value = "View a list of all emails in database")
    @ApiResponse(code=200, message="A list of emails retrieved successfully")
    public List<Email> getEmails() {
        return processing.getAll();
    }

    @PostMapping(consumes = "application/json")
    @ApiOperation(value = "Add a new email to the database. Should be in JSON format.")
    public void newEmail(@RequestBody Email email){
        processing.addNewEmail(email);
        emailSender.sendAll();
    }

    @PutMapping(value = "/{id}/date", produces = "application/json")
    @ApiOperation(value = "Update date of certain email")
    public ResponseEntity<String> updateDate(@PathVariable(value="id") int id,@RequestParam(value="date") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date newDate){
        List<Email> emails = processing.getAll();
        Boolean isPresent = emails.stream()
                .anyMatch( e -> e.getId() == id);

        if (!isPresent) {
            return ResponseEntity.status(404).body("404\nIndex out of range");
        }

        processing.changeDate(id, newDate);
        return ResponseEntity.ok(String.format("Delivery date of email with id: %s has been changed!", id));
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    @ApiOperation(value = "Delete email by id")
    public ResponseEntity<String> deleteEmail(@RequestParam(value="id") int id) {
        List<Email> emails = processing.getAll();
        Boolean isPresent = emails.stream()
                .anyMatch( e -> e.getId() == id);
        if (!isPresent) {
            return ResponseEntity.status(404).body("404\nIndex out of range");
        }
        processing.delete(id);
        return ResponseEntity.ok(String.format("Email with id: %s has been deleted!", id));
    }

}
