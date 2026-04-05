package com.royalfashionmart.service;

import com.royalfashionmart.entity.Order;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }
}