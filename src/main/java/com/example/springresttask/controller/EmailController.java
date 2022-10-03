package com.example.springresttask.controller;

import com.example.springresttask.domain.dto.EmailDto;
import com.example.springresttask.domain.mapper.EmailMapper;
import com.example.springresttask.service.EmailService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor

public class EmailController {

    private final EmailService emailService;
    private final EmailMapper emailMapper;

    @GetMapping
    public List<EmailDto> getAllPendingEmailDeliveries() {
        return emailService.pendingEmailDeliveries().stream()
                .map(emailMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmailDto addNewEmailDelivery(@Valid @RequestBody EmailDto emailDto) {
        return emailMapper.toDto(emailService.createEmailDelivery(emailMapper.toEntity(emailDto)));
    }

    @PutMapping
    public EmailDto updateDeliveryDate(@Valid @RequestBody EmailDto emailDto) {
        return emailMapper.toDto(emailService.updateDeliveryDate(emailMapper.toEntity(emailDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePendingEmail(@PathVariable Long id) {
        emailService.removePendingEmail(id);
    }
}
