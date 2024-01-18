package com.example.orderservice.externalservices;

import com.example.orderservice.models.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductService {
   @GetMapping("/products/{productId}")
   Product getfullProduct(@PathVariable UUID productId);
}
