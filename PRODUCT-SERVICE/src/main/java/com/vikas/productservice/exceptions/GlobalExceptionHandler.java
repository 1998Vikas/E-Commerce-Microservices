package com.vikas.productservice.exceptions;

import com.vikas.productservice.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {
      @ExceptionHandler(ProductNotExistException.class)
      public ResponseEntity<ErrorResponse> handleProductDoesNotExist(ProductNotExistException exception){
           ErrorResponse errorResponse=ErrorResponse.builder().message(exception.getMessage()).build();
           return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
      }
}
