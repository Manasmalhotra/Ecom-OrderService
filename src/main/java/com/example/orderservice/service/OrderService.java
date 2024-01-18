package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.models.Address;
import com.example.orderservice.models.Order;
import com.example.orderservice.models.Payment;

public interface OrderService {

    OrderResponseDTO placeOrder(int userId, int selectedAddressId, Payment payment);
}
