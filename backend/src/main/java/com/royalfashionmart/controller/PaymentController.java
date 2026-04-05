package com.royalfashionmart.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:5500")
public class PaymentController {

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String,Object>> createCheckoutSession(
            @RequestBody Map<String,Object> orderData,
            @RequestHeader("Authorization") String authHeader) {

        Map<String,Object> resp = new HashMap<>();
        try {
            System.out.println("Stripe payment order: " + orderData);

            // Dummy checkout URL (replace with Stripe SDK in production)
            resp.put("url", "https://checkout.stripe.com/pay/dummy-session-id");
            return ResponseEntity.ok(resp);

        } catch(Exception e){
            resp.put("message", e.getMessage());
            return ResponseEntity.status(500).body(resp);
        }
    }
}