package dev.br.pauloroberto.ms_email.controller;

import dev.br.pauloroberto.ms_email.dto.EmailDto;
import dev.br.pauloroberto.ms_email.model.Email;
import dev.br.pauloroberto.ms_email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = emailService.createEmail(emailDto);
        emailService.sendEmail(email);

        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Email>> list(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(emailService.list(pageable), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Email> list(@PathVariable Long id) {
        return new ResponseEntity<>(emailService.list(id), HttpStatus.OK);
    }
}
