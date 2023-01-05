package dev.br.pauloroberto.ms_email.service;

import dev.br.pauloroberto.ms_email.model.Email;
import dev.br.pauloroberto.ms_email.model.StatusEmail;
import dev.br.pauloroberto.ms_email.repository.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmailSenderService emailSenderService;

    public EmailService(EmailRepository emailRepository, EmailSenderService emailSenderService) {
        this.emailRepository = emailRepository;
        this.emailSenderService = emailSenderService;
    }

    public void sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());

        try { // Try to send the email
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());

            emailSenderService.sendEmail(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (Exception e) { // If an error occurs, set the status to ERROR
            email.setStatusEmail(StatusEmail.ERROR);
        } finally { // Save the email in the database
            emailRepository.save(email);
        }
    }
}
