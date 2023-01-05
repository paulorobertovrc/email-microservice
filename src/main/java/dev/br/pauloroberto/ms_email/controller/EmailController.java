package dev.br.pauloroberto.ms_email.controller;

import dev.br.pauloroberto.ms_email.dto.EmailDto;
import dev.br.pauloroberto.ms_email.model.Email;
import dev.br.pauloroberto.ms_email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email); // Copy properties from emailDto to email if they have the same name
        emailService.sendEmail(email);

        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
