package com.example.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Price price;
    private Category category;
    private int totalAvailableQuantity;
    private StockAvailabilityStatus stockAvailability;
}