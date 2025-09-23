package com.ecommerce.rutamtb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("message", "Ruta MTB Backend is running");
        health.put("version", "1.0.0");
        return ResponseEntity.ok(health);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Ruta MTB API");
        response.put("timestamp", LocalDateTime.now());
        response.put("endpoints", new String[]{
            "/health - Health check",
            "/rutamtb/auth/login - Login",
            "/rutamtb/auth/register - Register",
            "/rutamtb/products - Products"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Test endpoint working");
        response.put("timestamp", LocalDateTime.now());
        response.put("cors", "enabled");
        return ResponseEntity.ok(response);
    }
}