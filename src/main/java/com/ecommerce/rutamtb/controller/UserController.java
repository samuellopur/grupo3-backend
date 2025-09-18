package com.ecommerce.rutamtb.controller;

import com.ecommerce.rutamtb.model.User;
import com.ecommerce.rutamtb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    Obtiene todos los usuarios
    @GetMapping("/listuser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User created");
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable (value = "id") User user) {
        userService.deleteUser(user);
    }
}
