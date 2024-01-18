package com.example.orderservice.models;

public enum OrderStatus {
    PACKAGING("packaging"),
    SHIPPED("shipped"),
    OUT_FOR_DELIVERY("outfordelivery"),
    DELIVERED("delivered");

    String value;

    OrderStatus(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
