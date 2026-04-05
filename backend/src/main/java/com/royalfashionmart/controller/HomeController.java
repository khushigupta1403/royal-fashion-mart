package com.royalfashionmart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // MUST be @RestController
public class HomeController {

    @GetMapping("/")  // Handles the root URL
    public String home() {
        return "Royal Fashion Mart Backend Running 🚀";
    }
}