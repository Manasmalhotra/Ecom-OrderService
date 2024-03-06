package com.example.orderservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="CartItem")
@Getter
@Setter
public class CartItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long productId;
    int quantity;
    double pricePerItem;
}
