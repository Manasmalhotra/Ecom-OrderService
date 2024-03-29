package com.example.orderservice.models;

public enum OrderStatus {
    PENDING("Payment Pending"),
    PAYMENT_FAILED("Payment Failed"),
    CANCELLED("Cancelled"),
    PACKAGING("Packaging the order"),
    SHIPPED("Order Shipped"),
    OUT_FOR_DELIVERY("Order is out for delivery"),
    DELIVERED("Order Delivered");
    String value;

    OrderStatus(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }

}
