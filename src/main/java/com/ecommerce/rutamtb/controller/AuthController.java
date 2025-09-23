package com.ecommerce.rutamtb.controller;

import com.ecommerce.rutamtb.dto.*;
import com.ecommerce.rutamtb.model.User;
import com.ecommerce.rutamtb.repository.IUserRepository;
import com.ecommerce.rutamtb.security.CustomUserDetailsService;
import com.ecommerce.rutamtb.security.JwtUtil;
import com.ecommerce.rutamtb.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rutamtb/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Verificar si el usuario existe
            if (!userRepository.existsByEmail(loginRequest.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.UNAUTHORIZED.value(),
                        "Unauthorized",
                        "Invalid email or password",
                        "/rutamtb/auth/login"
                    ));
            }

            // Autenticar usuario
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            // Generar JWT token
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("name", userPrincipal.getName());
            extraClaims.put("lastName", userPrincipal.getLastName());
            extraClaims.put("userId", userPrincipal.getId());

            String token = jwtUtil.generateToken(userPrincipal.getUsername(), extraClaims);

            // Calcular fechas
            LocalDateTime issuedAt = LocalDateTime.now();
            LocalDateTime expiresAt = issuedAt.plusDays(1); // 24 horas

            // Crear respuesta
            JwtResponse jwtResponse = new JwtResponse(
                token,
                userPrincipal.getUsername(),
                userPrincipal.getName(),
                userPrincipal.getLastName(),
                issuedAt,
                expiresAt
            );

            return ResponseEntity.ok(jwtResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.UNAUTHORIZED.value(),
                    "Unauthorized",
                    "Invalid email or password",
                    "/rutamtb/auth/login"
                ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            // Verificar si el email ya existe
            if (userRepository.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        "Conflict",
                        "Email is already in use",
                        "/rutamtb/auth/register"
                    ));
            }

            // Verificar si el username ya existe
            if (userRepository.existsByUsername(registerRequest.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        "Conflict",
                        "Username is already in use",
                        "/rutamtb/auth/register"
                    ));
            }

            // Crear nuevo usuario
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setName(registerRequest.getName());
            user.setLastName(registerRequest.getLastName());
            user.setAddress(registerRequest.getAddress());
            user.setPhone(registerRequest.getPhone());
            user.setRegisterDate(new Date());

            // Guardar usuario
            User savedUser = userRepository.save(user);

            // Generar token automáticamente para el usuario registrado
            UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmail());
            
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("name", savedUser.getName());
            extraClaims.put("lastName", savedUser.getLastName());
            extraClaims.put("userId", savedUser.getId_User());

            String token = jwtUtil.generateToken(userDetails.getUsername(), extraClaims);

            // Calcular fechas
            LocalDateTime issuedAt = LocalDateTime.now();
            LocalDateTime expiresAt = issuedAt.plusDays(1); // 24 horas

            // Crear respuesta
            JwtResponse jwtResponse = new JwtResponse(
                token,
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getLastName(),
                issuedAt,
                expiresAt
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error creating user: " + e.getMessage(),
                    "/rutamtb/auth/register"
                ));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Authorization header");
            }

            String token = authHeader.substring(7);
            
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractUsername(token);
                Long remainingTime = jwtUtil.getExpirationTime(token);
                
                Map<String, Object> response = new HashMap<>();
                response.put("valid", true);
                response.put("email", email);
                response.put("remainingTimeMs", remainingTime);
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Token validation failed: " + e.getMessage());
        }
    }
}