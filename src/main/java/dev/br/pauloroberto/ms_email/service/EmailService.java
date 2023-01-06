package dev.br.pauloroberto.ms_email.service;

import dev.br.pauloroberto.ms_email.dto.EmailDto;
import dev.br.pauloroberto.ms_email.model.Email;
import dev.br.pauloroberto.ms_email.model.StatusEmail;
import dev.br.pauloroberto.ms_email.repository.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    public EmailService(EmailRepository emailRepository,JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    public Email createEmail(EmailDto emailDto) {
        Email email = new Email();

        email.setSendDateEmail(LocalDateTime.now());
        BeanUtils.copyProperties(emailDto, email);

        return email;
    }

    public void sendEmail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());

            javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (Exception e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(email);
        }
    }

    public Iterable<Email> list(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }

    public Email list(Long id) {
        return emailRepository.findById(id).orElseThrow();
    }
}
