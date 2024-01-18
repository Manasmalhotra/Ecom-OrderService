package com.example.orderservice.dto;

import com.example.orderservice.models.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDTO {
    String orderID;
    String paymentId;
    String paymentStatus;
    String orderStatus;
    String message;
    String deliveryAddress;
    public OrderResponseDTO(String orderID,String paymentId,String orderStatus, String message,String deliveryAddress) {
        this.orderID=orderID;
        this.orderStatus=orderStatus;
        this.message=message;
        this.paymentId=paymentId;
        this.deliveryAddress=deliveryAddress;
    }
}
