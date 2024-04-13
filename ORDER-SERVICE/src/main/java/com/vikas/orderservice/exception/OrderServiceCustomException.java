package com.vikas.orderservice.exception;

import lombok.Data;

@Data
public class OrderServiceCustomException extends  Exception{
    public OrderServiceCustomException(String message) {
        super(message);
    }
}
