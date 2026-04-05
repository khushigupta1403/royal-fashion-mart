package com.royalfashionmart.service;

import com.royalfashionmart.entity.CartItem;
import com.royalfashionmart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository repo;

    public CartItem add(CartItem item) {
        return repo.save(item);
    }

    public List<CartItem> getCart(Long userId) {
        return repo.findByUserId(userId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}