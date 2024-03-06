package com.example.orderservice.externalservices;

import com.example.orderservice.models.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface AddressService {
    @GetMapping("user/{userId}/deliveryAddress/{addressId}")
    Address getAddress(@PathVariable int userId, @PathVariable int addressId);
}
