package com.example.orderservice.service;

import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.exceptions.PaymentFailedException;
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
    public OrderResponseDTO placeOrder(int userId,int selectedAddressId,Payment payment) {
        Cart cart= cartService.getCart(userId);
        List<OrderItem>orderItems=cart.getCartItems().stream().map(item->getOrderItem(item)).collect(Collectors.toList());
        Order order=new Order();
        order.setOrderItems(orderItems);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        Address address=userService.getAddress(userId,selectedAddressId);
        StringBuilder shippingAddress=new StringBuilder(address.getHouseNumber()+","+address.getStreetName()+address.getArea()+address.getPinCode());
        if(address.getLandmark()!=null){
            shippingAddress.append(address.getLandmark());
        }
        order.setShippingAddress(address);
        order.setPaymentID(payment.getId());
        Order placedOrder=orderRepository.save(order);
        OrderResponseDTO orderResponse=new OrderResponseDTO();
        orderResponse.setOrderID(order.getId().toString());
        OrderStatus orderStatus=order.getOrderStatus();
        orderResponse.setOrderStatus(orderStatus.getValue());
        if(payment.getPaymentStatus().equals(PaymentStatus.FAILED)){
            throw new PaymentFailedException(payment.getId(), payment.getAmount());
        }
        orderResponse.setPaymentId(order.getPaymentID().toString());
        orderResponse.setPaymentStatus(payment.getPaymentStatus().getValue());
        orderResponse.setMessage("Order Placed!");
        return orderResponse;
    }

    private OrderItem getOrderItem(CartItem item) {
        OrderItem orderItem=new OrderItem();
        orderItem.setProduct(item.getProduct());
        orderItem.setQuantity(item.getQuantity());
        orderItem.setTotalPrice(item.getTotalPrice());
        orderItem.setDeliveryDate(LocalDateTime.now().plusDays(7));
        return orderItem;
    }


}
