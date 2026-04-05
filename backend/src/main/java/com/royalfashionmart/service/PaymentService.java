package com.royalfashionmart.service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    public PaymentService() {
        Stripe.apiKey = "sk_test_YourStripeSecretKey"; // 🔑 Replace with your Stripe Secret Key
    }

    public Session createStripeSession(String email, List<Object> items) throws Exception {
        SessionCreateParams.LineItem[] lineItems = items.stream().map(item -> {
            // Assuming each item is { name, price, quantity }
            var map = (java.util.Map<String,Object>) item;
            return SessionCreateParams.LineItem.builder()
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd")
                                    .setUnitAmount(((Number) map.get("price")).longValue()) // in cents
                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                            .setName((String) map.get("name"))
                                            .build())
                                    .build()
                    )
                    .setQuantity(((Number) map.get("quantity")).longValue())
                    .build();
        }).toArray(SessionCreateParams.LineItem[]::new);

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://127.0.0.1:5500/success.html")
                .setCancelUrl("http://127.0.0.1:5500/checkout.html")
                .setCustomerEmail(email)
                .addAllLineItem(java.util.Arrays.asList(lineItems))
                .build();

        return Session.create(params);
    }
}