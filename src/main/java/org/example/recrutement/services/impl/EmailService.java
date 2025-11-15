package org.example.recrutement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String username, String token) {
        String subject = "Reset Your Password";
        String resetUrl = "http://localhost:4200/reset-password?token=" + token;

        String body = """
                Hello,
                
                You requested to reset your password.
                Click the link below to reset it:

                %s

                If you didn’t request this, you can ignore this email.

                Thanks,
                Your App Team
                """.formatted(resetUrl);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(username);           // email du destinataire
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
