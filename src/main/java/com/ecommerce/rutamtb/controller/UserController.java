package com.ecommerce.rutamtb.controller;

//import com.ecommerce.rutamtb.dto.UserDto;
import com.ecommerce.rutamtb.model.User;
import com.ecommerce.rutamtb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutamtb/users")
public class UserController {

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    /*@Autowired
    private JwtUtil jwtUtil;*/

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obtiene todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> listAllUser() {
        List<User> users = userService.printAllUsers();
        return ResponseEntity.ok(users);
    }

    // Obtiene un usuario por ID
    @GetMapping("/search/{id}")
    public ResponseEntity<User> searchUserById(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crea un nuevo usuario
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating user: " + e.getMessage());
        }
    }

    // Actualiza un usuario existente
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody User user) {
        try {
            User existingUser = userService.findUserById(id);
            if (existingUser != null) {
                user.setId_User(id); // Asegurar que el ID coincida
                userService.saveUser(user);
                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating user: " + e.getMessage());
        }
    }

    // Elimina un usuario por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                userService.deleteUser(user);
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }
}