package com.royalfashionmart.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.royalfashionmart.repository.UserRepository;
import com.royalfashionmart.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        userRepository.save(user);

        response.put("message", "User registered successfully ✅");
        return ResponseEntity.ok(response);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        Map<String, Object> response = new HashMap<>();

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {

            response.put("message", "Login successful ✅");
            response.put("name", user.getName());

            return ResponseEntity.ok(response);

        } else {
            response.put("message", "Invalid email or password ❌");
            return ResponseEntity.status(401).body(response);
        }
    }
}