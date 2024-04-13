package com.vikas.orderservice.services;

import com.vikas.orderservice.payload.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
