package com.project.back_end.services;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(String email) {
        return "jwt-token";
    }
    public String getSigningKey() {
        return "secret";
    }
}
