package com.vikas.orderservice.services.impl;

import com.vikas.orderservice.client.ProductService;
import com.vikas.orderservice.entity.Order;
import com.vikas.orderservice.payload.OrderRequest;
import com.vikas.orderservice.repository.OrderRepository;
import com.vikas.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    @Autowired
    private  ProductService productService;
@Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        //before placing order checking  if available in inventory
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
      Order order= Order.builder().
              productId(orderRequest.getProductId())
              .price(orderRequest.getAmount())
              .orderDate(Instant.now())
              .orderStatus("CREATED")
              .quantity(orderRequest.getQuantity())
              .build();
        Order save = orderRepository.save(order);
        return save.getOrderId();
    }
}
