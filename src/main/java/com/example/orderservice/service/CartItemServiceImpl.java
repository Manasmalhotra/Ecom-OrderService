package com.example.orderservice.service;

import com.example.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.externalservices.ProductService;
import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Product;
import com.example.orderservice.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService{
    CartItemRepository cartItemRepository;
    ProductService productService;
    public CartItemServiceImpl(CartItemRepository cartItemRepository,ProductService productService){
        this.cartItemRepository=cartItemRepository;
        this.productService=productService;
    }
    @Override
    public CartItem createCartItem(long productId, int quantity) {
        CartItem cartItem=new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        Product product= productService.getfullProduct(productId);
        double pricePerItem=product.getPrice().getAmount();
        double discountedPricePerItem=pricePerItem-pricePerItem*product.getPrice().getDiscountPercentage();
        cartItem.setPricePerItem(discountedPricePerItem);
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItemQuantity(long cartItemId,int quantity){
        if(quantity==0){
            deleteCartItem(cartItemId);
            return null;
        }
        CartItem itemToBeUpdated=cartItemRepository.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("Cart Item","Cart ID",Long.toString(cartItemId)));
        itemToBeUpdated.setQuantity(quantity);
        return cartItemRepository.save(itemToBeUpdated);
    }

    @Override
    public void deleteCartItem(long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
