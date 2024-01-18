package com.example.orderservice.service;

import com.example.orderservice.models.Cart;

import java.util.UUID;

public interface CartService {
    Cart addToCart(UUID cartId,UUID productId);

    Cart createCart();
    Cart getCart(int userId);
    boolean allCartItemsAvailableOnCheckout(UUID cartId);
}
