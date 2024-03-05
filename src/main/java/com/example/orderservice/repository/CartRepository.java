package com.example.orderservice.repository;

import com.example.orderservice.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByUserId(long userId);
}
