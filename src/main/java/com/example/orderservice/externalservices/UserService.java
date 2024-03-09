package com.example.orderservice.externalservices;


import com.example.orderservice.models.Address;
import com.example.orderservice.models.UserEntity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("USER-SERVICE")
public interface UserService {
    @GetMapping("/users/viewprofile/{id}")
    UserEntity getfullUser(@PathVariable int id);
    @GetMapping("/getUser")
    UserEntity getUserByEmailOrMobileNumber(String mobileOremail);
    @GetMapping("user/{userId}/deliveryAddress/{addressId}")
    Address getAddress(@PathVariable int userId, @PathVariable int addressId);
}
