package com.example.orderservice.service;

import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.models.OrderStatus;


public interface OrderService {

    OrderResponseDTO placeOrder(int userId, int selectedAddressId);
    void updateOrder(long orderId, OrderStatus orderStatus);
}
