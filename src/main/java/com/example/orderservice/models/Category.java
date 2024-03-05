package com.example.orderservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Category extends BaseModel{
    String categoryName;
}
