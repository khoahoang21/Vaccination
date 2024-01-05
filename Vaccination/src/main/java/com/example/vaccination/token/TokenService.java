/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.vaccination.token;

import com.example.vaccination.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Autowired
    private final TokenRepository tokenRepository;

    public Token findByEmp(Employee employee) {
        Token token = tokenRepository.findByEmployee(employee)
                .orElse(null);
        return token;
    }

    public Token findByValue(String value) {
        Token token = tokenRepository.findByValue(value)
                .orElse(null);
        return token;
    }

    public void delete(Token authToken) {
        tokenRepository.delete(authToken);
    }

    public Token save(Token authToken) {
        return tokenRepository.save(authToken);
    }

    public boolean isValid(Token token) {
        return !token.getExpiredAt().isBefore(LocalDateTime.now());
    }
}
