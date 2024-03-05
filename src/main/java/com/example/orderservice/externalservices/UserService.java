package com.example.orderservice.externalservices;


import com.example.orderservice.models.UserEntity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("UserService")
public interface UserService {
    @GetMapping("/viewprofile/{id}")
    UserEntity getfullUser(@PathVariable int id);
    @GetMapping("/getUser")
    UserEntity getUserByEmailOrMobileNumber(String mobileOremail);
}
