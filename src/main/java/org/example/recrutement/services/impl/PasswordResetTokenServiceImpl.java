package org.example.recrutement.services.impl;

import org.example.recrutement.entities.PasswordResetToken;
import org.example.recrutement.repositories.PasswordResetTokenRepository;
import org.example.recrutement.services.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Override
    public void createToken(String email, String token) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUserEmail(email);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 30); // expiration 30 minutes
        resetToken.setExpiryDate(cal.getTime());

        tokenRepository.save(resetToken);
    }

    @Override
    public String validateToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElse(null);
        if (resetToken == null) return null;
        if (resetToken.getExpiryDate().before(new Date())) return null;
        return resetToken.getUserEmail();
    }
}
