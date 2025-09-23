package com.ecommerce.rutamtb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    
    private String token;
    private String type = "Bearer";
    private String email;
    private String name;
    private String lastName;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    
    public JwtResponse(String token, String email, String name, String lastName, 
                     LocalDateTime issuedAt, LocalDateTime expiresAt) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }
}