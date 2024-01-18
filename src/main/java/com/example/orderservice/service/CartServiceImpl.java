package com.example.orderservice.service;

import com.example.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.externalservices.ProductService;
import com.example.orderservice.models.Cart;
import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Product;
import com.example.orderservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;
    ProductService productService;
    CartItemService cartItemService;
    public CartServiceImpl(ProductService productService, CartItemService cartItemService, CartRepository cartRepository){
        this.productService=productService;
        this.cartItemService=cartItemService;
        this.cartRepository=cartRepository;
    }
    @Override
    public Cart addToCart(UUID cartId,UUID productId) {
        Cart cart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart","ID",cartId.toString()));
        List<CartItem> cartItemList=cart.getCartItems();
        if(cartItemList==null){
            cartItemList=new ArrayList<>();
        }
        int found=0;
        for(CartItem item:cartItemList){
            if(item.getProduct().getId().equals(productId)){
                item.setQuantity(item.getQuantity()+1);
                found=1;
                break;
            }
        }
        if(found==0){
            Product product=productService.getfullProduct(productId);
            CartItem cartItem=cartItemService.createCartItem(product,1);
            cartItemList.add(cartItem);
        }
        cart.setCartItems(cartItemList);
        return cartRepository.save(cart);
    }

    @Override
    public Cart createCart() {
        Cart cart=new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(int userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public boolean allCartItemsAvailableOnCheckout(UUID cartId) {
        Optional<Cart> cart=cartRepository.findById(cartId);
        if(cart.isPresent()){
            List<CartItem>cartItems=cart.get().getCartItems();
            for(CartItem cartItem:cartItems){
                if(cartItem.getProduct().getTotalAvailableQuantity()<cartItem.getQuantity()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
