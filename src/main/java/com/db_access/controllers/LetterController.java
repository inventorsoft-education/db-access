package com.db_access.controllers;

import com.db_access.entity.Letter;
import com.db_access.service.LetterService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "letters")
public class LetterController {

    private LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping
    public ResponseEntity<String> saveLetter(@RequestBody Letter letter) {
        letterService.save(letter);
        return ResponseEntity.ok("Letter saved");
    }

    @DeleteMapping(value = {"id"})
    public ResponseEntity<String> deleteLetter(@PathVariable Long id) {
        letterService.deleteById(id);
        return ResponseEntity.ok("Letter deleted");
    }

    @PutMapping
    public ResponseEntity<String> updateLetter(@RequestBody Letter letter) {
        letterService.update(letter);
        return ResponseEntity.ok("Letter updated");
    }

    @GetMapping(value = {"id"})
    public ResponseEntity<Letter> getLetterById(@PathVariable Long id) {
        return ResponseEntity.ok(letterService.getById(id));
    }

    @GetMapping
    public List<Letter> getAllLetters() {
        return letterService.getAll();
    }

    @GetMapping(value = "{recipient}")
    public List<Letter> getLetterByRecipient(@PathVariable String recipient) {
        return letterService.getByRecipient(recipient);
    }

}
