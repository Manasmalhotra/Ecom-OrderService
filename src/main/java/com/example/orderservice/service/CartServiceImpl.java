package com.example.orderservice.service;

import com.example.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.externalservices.ProductService;
import com.example.orderservice.externalservices.UserService;
import com.example.orderservice.models.Cart;
import com.example.orderservice.models.CartItem;
import com.example.orderservice.models.Product;
import com.example.orderservice.models.UserEntity;
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
    UserService userService;
    public CartServiceImpl(ProductService productService, CartItemService cartItemService, CartRepository cartRepository,UserService userService){
        this.productService=productService;
        this.cartItemService=cartItemService;
        this.cartRepository=cartRepository;
        this.userService=userService;
    }
    @Override
    public Cart createCart(long userId) {
        System.out.println("Cart");
        Cart cart=new Cart();
        cart.setUserId(userId);
        cart.setCartItems(new ArrayList<>());
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(UUID cartId, long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Cart updateCartItem(UUID cartId, long cartItemId,int quantity) {
        cartItemService.updateCartItemQuantity(cartItemId,quantity);
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Cart addToCart(UUID cartId,long productId) {
        Cart cart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart","ID",cartId.toString()));
        List<CartItem> cartItemList=cart.getCartItems();
        int found=0;
        for(CartItem item:cartItemList){
            if(item.getProductId()==productId){
                item.setQuantity(item.getQuantity()+1);
                found=1;
                break;
            }
        }
        if(found==0){
            CartItem cartItem=cartItemService.createCartItem(productId,1);
            cartItemList.add(cartItem);
        }
        cart.setCartItems(cartItemList);
        return cartRepository.save(cart);
    }


    @Override
    public Cart getCart(long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


}
