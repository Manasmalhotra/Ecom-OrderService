package com.example.orderservice.models;

public enum PaymentStatus {
    PENDING("pending"),
    CREATED("created"),
    AUTHORIZED("authorized"),
    CAPTURED("captured"),
    REFUNDED("refunded"),
    FAILED("failed");
    String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
