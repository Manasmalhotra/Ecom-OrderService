package com.example.orderservice.service;

import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Product;

public interface CartItemService {
    CartItem createCartItem(Product product,int quantity);
}
