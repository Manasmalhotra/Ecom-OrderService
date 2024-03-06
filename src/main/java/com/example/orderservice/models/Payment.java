package com.example.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;


public class Payment {
    UUID id;
    int amount;
    String currency;
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
}