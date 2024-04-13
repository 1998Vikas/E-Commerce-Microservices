package com.vikas.orderservice.exception.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikas.orderservice.exception.ErrorResponse;
import com.vikas.orderservice.exception.OrderServiceCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return  new OrderServiceCustomException(errorResponse.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       
    }
}
