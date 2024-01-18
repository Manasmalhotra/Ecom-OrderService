package com.example.orderservice.exceptions;

import java.util.UUID;

public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(UUID paymentId, double amount) {
        super("Payment failed! Transaction ID: "+paymentId+" Amount: "+amount);
    }
}
