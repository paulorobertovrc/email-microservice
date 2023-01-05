package dev.br.pauloroberto.ms_email.service;

import dev.br.pauloroberto.ms_email.repository.EmailRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }
}
