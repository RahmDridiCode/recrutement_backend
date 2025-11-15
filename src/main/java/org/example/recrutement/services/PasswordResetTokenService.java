package org.example.recrutement.services;

public interface PasswordResetTokenService {
    public void createToken(String email, String token);
    public String validateToken(String token);
}
