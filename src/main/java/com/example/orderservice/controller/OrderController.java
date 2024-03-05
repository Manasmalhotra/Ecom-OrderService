package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.models.OrderStatus;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/checkout/{addressId}")
    public ResponseEntity<OrderResponseDTO> checkOut(@PathVariable(name="addressId") int selectedAddressId){

        int userId=Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(orderService.placeOrder(userId,selectedAddressId));
    }
    @PutMapping("/updateOrder/{orderId}")
    public void updateOrder(@PathVariable long orderId,@RequestBody OrderStatus orderStatus){
        orderService.updateOrder(orderId,orderStatus);
    }
}
