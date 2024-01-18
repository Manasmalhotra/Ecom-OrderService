package com.example.orderservice.dto;

import com.example.orderservice.models.Order;
import com.example.orderservice.models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    Order order;
    Payment payment;
}
