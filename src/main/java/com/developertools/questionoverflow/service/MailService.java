package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.request.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void send(SendMailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(request.getBody());
        mailMessage.setSubject(request.getTitle());
        mailMessage.setTo(request.getTo());
        mailMessage.setFrom("question-app");

        mailSender.send(mailMessage);
    }
}
