package com.example.workflow.services;

import com.example.workflow.models.EmailMessage;
import com.example.workflow.repositories.EmailMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailMessageRepo emailMessageRepo;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        EmailMessage emailMessage = new EmailMessage(to, subject, body);
        emailMessageRepo.save(emailMessage);
    }
}
