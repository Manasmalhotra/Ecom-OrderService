package com.example.orderservice.service;

import com.example.orderservice.models.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart addToCart(UUID cartId,long productId);

    Cart createCart(long userId);
    Cart removeItemFromCart(UUID cartId,long cartItemId);
    Cart updateCartItem(UUID cartId,long cartItemId,int quantity);
    Cart getCart(long userId);

    List<Cart> getAllCarts();
}
