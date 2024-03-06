package com.example.orderservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="Cart")
@Getter
@Setter
public class Cart extends BaseModel{
    long userId;
    @OneToMany
    List<CartItem> cartItems;
}
