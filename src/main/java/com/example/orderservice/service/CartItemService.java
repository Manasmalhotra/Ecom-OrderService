package com.example.orderservice.service;

import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Product;

public interface CartItemService {
    CartItem createCartItem(long productId,int quantity);
    CartItem updateCartItemQuantity(long cartItemId,int quantity);

    void deleteCartItem(long cartItemId);
}
