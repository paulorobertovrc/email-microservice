package dev.br.pauloroberto.ms_email.controller;

import dev.br.pauloroberto.ms_email.service.EmailService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
}
