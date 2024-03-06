package com.example.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    int id;
    UserEntity user;
    String houseNumber;
    String streetName;
    String area;
    String landmark;
    String pinCode;
}
