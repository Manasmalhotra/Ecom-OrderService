package com.example.orderservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="Cart")
@Getter
@Setter
public class Cart extends BaseModel{
    @OneToOne
    UserEntity user;
    @ManyToMany(cascade = CascadeType.ALL)
    List<CartItem> cartItems;
}
