package com.royalfashionmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.royalfashionmart.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // No extra code needed here for basic CRUD
}