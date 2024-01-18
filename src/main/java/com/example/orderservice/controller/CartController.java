package com.example.orderservice.controller;

import com.example.orderservice.models.Cart;
import com.example.orderservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("{userId}/cart")
public class CartController {
    CartService cartService;
    @PostMapping
    ResponseEntity<Cart>createCart(){
       return ResponseEntity.ok(cartService.createCart()); 
    }
    @PutMapping("{cartId}/addProduct/{productId}")
    ResponseEntity<Cart>addProductToCart(@PathVariable UUID cartId,@PathVariable UUID productId){
        return  ResponseEntity.ok(cartService.addToCart(cartId,productId));
    }
}
