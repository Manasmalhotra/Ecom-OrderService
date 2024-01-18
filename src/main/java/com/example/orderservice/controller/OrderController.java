package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.models.Order;
import com.example.orderservice.models.Payment;
import com.example.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("{userId}/order")
public class OrderController {
    OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/checkout/{addressId}")
    public ResponseEntity<OrderResponseDTO> checkOut(@PathVariable int userId, @PathVariable int selectedAddressId, @RequestBody Payment payment){
        return ResponseEntity.ok(orderService.placeOrder(userId,selectedAddressId,payment));
    }
}
