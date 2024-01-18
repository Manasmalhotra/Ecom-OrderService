package com.example.orderservice.service;

import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Product;
import com.example.orderservice.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
    CartItemRepository cartItemRepository;
    public CartItemServiceImpl(CartItemRepository cartItemRepository){
        this.cartItemRepository=cartItemRepository;
    }
    @Override
    public CartItem createCartItem(Product product, int quantity) {
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        double pricePerItem=product.getPrice().getAmount();
        double discountedPricePerItem=pricePerItem-pricePerItem*product.getPrice().getDiscountPercentage();
        cartItem.setTotalPrice(discountedPricePerItem*quantity);
        return cartItemRepository.save(cartItem);
    }
}
