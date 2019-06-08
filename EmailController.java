package com.example.demo.controller;

import com.example.demo.Email;
import com.example.demo.repository.JDBCEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController()
@RequestMapping("/Email")
public class EmailController {

    JDBCEmailRepository jdbcEmailRepository;

    @Autowired
    public EmailController(JDBCEmailRepository jdbcEmailRepository) {
        this.jdbcEmailRepository = jdbcEmailRepository;
    }

    @RequestMapping(value = "/activeEmails", method=GET)
    private ArrayList<Email> showsActiveEmails() throws SQLException {
       return jdbcEmailRepository.getActive();
    }

    @RequestMapping(value = "/newEmail", method=POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    private void addNew(@RequestBody Email newEmail) throws SQLException {
        jdbcEmailRepository.addNewEmail(newEmail);
    }

    @DeleteMapping("/{id}")
    private void deleteOne(@PathVariable int id){
        jdbcEmailRepository.deleteOneEmail(id);
    }

    @PutMapping(value="/newDateForOneEmail")
    private  void updateDeliveryDateForOneEmail  (@RequestParam int  id,
                                                  @RequestParam Date date){
        jdbcEmailRepository.updateDeliveryDateForOneEmail(id, (java.sql.Date) date);

    }

    @RequestMapping(value = "/allEmails", method = GET)
    private ArrayList<Email> getAllEmails(){
        return jdbcEmailRepository.getAll();
    }





}
