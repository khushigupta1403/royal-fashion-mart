package com.royalfashionmart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Value("${openrouter.api.key}")  // Your OpenRouter API key
    private String apiKey;

    private final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody Map<String, String> body) {

        try {
            String userMsg = body.get("message");
            if (userMsg == null || userMsg.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("reply", "Message is empty"));
            }

            RestTemplate restTemplate = new RestTemplate();

            // HEADERS
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            // REQUEST BODY
            Map<String, Object> request = new HashMap<>();
            request.put("model", "gpt-3.5-turbo"); // ✅ Correct model

            // MESSAGES
            List<Map<String, String>> messages = new ArrayList<>();

            Map<String, String> systemMsg = Map.of(
                    "role", "system",
                    "content", "You are a helpful shopping assistant for Royal Fashion Mart. Help users with products, orders, tracking, payments."
            );

            Map<String, String> userMessage = Map.of(
                    "role", "user",
                    "content", userMsg
            );

            messages.add(systemMsg);
            messages.add(userMessage);
            request.put("messages", messages);

            // POST REQUEST
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
            Map response = restTemplate.postForObject(API_URL, entity, Map.class);

            // PARSE RESPONSE
            List choices = (List) response.get("choices");
            Map first = (Map) choices.get(0);
            Map message = (Map) first.get("message");
            String reply = message.get("content").toString();

            return ResponseEntity.ok(Map.of("reply", reply));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}