package com.example.orderservice.models;

import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseModel{
    private double amount;
    private double discountPercentage;

    public double amountToBePaid(){
        return amount-amount*discountPercentage;
    }

}
