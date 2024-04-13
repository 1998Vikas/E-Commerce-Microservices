package com.vikas.productservice.exceptions;

import org.springframework.stereotype.Component;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String s) {
        super(s);
    }
}