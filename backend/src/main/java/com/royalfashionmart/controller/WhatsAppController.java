package com.royalfashionmart.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppController {

    // Twilio credentials (replace with your own)
    private static final String ACCOUNT_SID = "YOUR_TWILIO_SID";
    private static final String AUTH_TOKEN = "YOUR_TWILIO_AUTH_TOKEN";
    private static final String FROM_NUMBER = "whatsapp:+14155238886"; // Twilio sandbox

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    // Call this endpoint to send WhatsApp messages
    @PostMapping("/send")
    public String sendWhatsApp(@RequestParam String to, @RequestParam String message){
        Message msg = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + to),
                new com.twilio.type.PhoneNumber(FROM_NUMBER),
                message
        ).create();
        return "Sent: " + msg.getSid();
    }
}