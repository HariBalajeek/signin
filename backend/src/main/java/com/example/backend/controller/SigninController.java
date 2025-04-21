package com.example.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Signin;
import com.example.backend.repository.SigninRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class SigninController {
    @Autowired
    private SigninRepository signinRepository;

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody Signin user) {
        Optional<Signin> existingUser = signinRepository.findByEmail(user.getEmail());
        System.out.println("Entered password: " + user.getPassword());
        if (existingUser.isPresent()) {
            return ResponseEntity.ok("Login successful! Welcome " + existingUser.get().getEmail());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials. Please try again.");
        }
        
// System.out.println("Stored hashed password: " + dbUser.getPassword());

    }

    @PostMapping("/signup")
public ResponseEntity<String> signup(@RequestBody Signin newUser) {
    // Optional: Check if email already exists
    Optional<Signin> existingUser = signinRepository.findByEmail(newUser.getEmail());

    if (existingUser.isPresent()) {
        return ResponseEntity.status(400).body("Email already registered.");
    }

    signinRepository.save(newUser);
    return ResponseEntity.ok("Signup successful! You can now sign in.");
}

    
}
