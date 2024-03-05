package com.example.orderservice.controller;

import com.example.orderservice.models.Cart;
import com.example.orderservice.service.CartItemService;
import com.example.orderservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;

    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @GetMapping("/getCart")
    ResponseEntity<Cart>getCartByUserId(){
        int userId=Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(cartService.getCart(userId));
    }
    @PostMapping("/createCart")
    ResponseEntity<Cart>createCart(){
        int userId=Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
       return ResponseEntity.ok(cartService.createCart(userId));
    }
    @PutMapping("/{cartId}/addProduct/{productId}")
    ResponseEntity<Cart>addProductToCart(@PathVariable UUID cartId,@PathVariable long productId){
        return  ResponseEntity.ok(cartService.addToCart(cartId,productId));
    }
    @DeleteMapping("/{cartId}/removeProduct/{cartItemId}")
    ResponseEntity<Cart>removeProductFromCart(@PathVariable UUID cartId,@PathVariable long cartItemId){
        return ResponseEntity.ok(cartService.removeItemFromCart(cartId,cartItemId));
    }
    @PutMapping("/{cartId}/updateProduct/{cartItemId}")
    ResponseEntity<Cart>updateProduct(@PathVariable UUID cartId,@PathVariable long cartItemId,@RequestBody int quantity){
        return ResponseEntity.ok(cartService.updateCartItem(cartId,cartItemId,quantity));
    }
}
