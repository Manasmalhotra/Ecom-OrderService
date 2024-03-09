package com.example.orderservice.service;

import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.externalservices.UserService;
import com.example.orderservice.models.*;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;
    UserService userService;
    CartService cartService;
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService,CartService cartService){
        this.orderRepository=orderRepository;
        this.userService=userService;
        this.cartService=cartService;
    }

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(int userId,int selectedAddressId) {
        Cart cart= cartService.getCart(userId);
        List<OrderItem>orderItems=cart.getCartItems().stream().map(item->getOrderItem(item)).collect(Collectors.toList());
        Order order=new Order();
        order.setOrderItems(orderItems);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        Address address=userService.getAddress(userId,selectedAddressId);
        /*StringBuilder shippingAddress=new StringBuilder(address.getHouseNumber()+","+address.getStreetName()+address.getArea()+address.getPinCode());
        if(address.getLandmark()!=null){
            shippingAddress.append(address.getLandmark());
        }*/
        order.setAddressId(selectedAddressId);
        order.setOrderStatus(OrderStatus.PENDING);
        Order savedOrder=orderRepository.save(order);
        OrderResponseDTO orderResponse=new OrderResponseDTO();
        orderResponse.setOrderID(String.valueOf(savedOrder.getId()));
        OrderStatus orderStatus=savedOrder.getOrderStatus();
        orderResponse.setOrderStatus(orderStatus.getValue());
        orderResponse.setPaymentStatus(String.valueOf(PaymentStatus.PENDING));
        orderResponse.setMessage("Order Placed, Waiting for the payment.");
        return orderResponse;
    }
    private Order getOrder(long orderId){
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order","ID",String.valueOf(orderId)));
        return order;
    }
    @Override
    public void updateOrder(long orderId,OrderStatus orderStatus) {
        Order order=getOrder(orderId);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    private OrderItem getOrderItem(CartItem item) {
        OrderItem orderItem=new OrderItem();
        orderItem.setProductId(item.getProductId());
        orderItem.setQuantity(item.getQuantity());
        orderItem.setTotalPrice(item.getPricePerItem());
        orderItem.setDeliveryDate(LocalDateTime.now().plusDays(7));
        return orderItem;
    }
}
