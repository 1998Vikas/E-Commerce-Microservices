package com.vikas.productservice.services;

import com.vikas.productservice.exceptions.ProductNotExistException;
import com.vikas.productservice.payload.ProductRequest;
import com.vikas.productservice.payload.ProductResponse;

public interface ProductService {
    Long saveProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId) throws ProductNotExistException;

    void reduceQuantity(Long productId, int quantity) throws ProductNotExistException;
}
