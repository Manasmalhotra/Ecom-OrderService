package com.example.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="OrderItem")
@Getter
@Setter
public class OrderItem extends BaseModel{
    long productId;
    int quantity;
    double totalPrice;
    LocalDateTime deliveryDate;
}
