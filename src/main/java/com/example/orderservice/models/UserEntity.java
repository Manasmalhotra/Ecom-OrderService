package com.example.orderservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@Getter
@Setter
public class UserEntity {
    int id;
    String firstName;
    String lastName;
    String email;
    String password;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    LocalDate dateOfBirth;
    String mobileNo;
    List<Address> address;
    LocalDateTime createdAt;
    Role role;
}
