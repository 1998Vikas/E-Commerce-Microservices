package com.vikas.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
      @ExceptionHandler(OrderServiceCustomException.class)
      public ResponseEntity<ErrorResponse> handleProductDoesNotExist(OrderServiceCustomException exception){
           ErrorResponse errorResponse=ErrorResponse.builder().message(exception.getMessage()).build();
           return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
      }
}
