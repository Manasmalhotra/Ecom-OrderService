package com.example.orderservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="Category")
@Getter
@Setter
public class Category extends BaseModel{
    String categoryName;
}
