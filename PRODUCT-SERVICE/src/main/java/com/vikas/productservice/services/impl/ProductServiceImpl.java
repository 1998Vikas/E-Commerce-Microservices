package com.vikas.productservice.services.impl;

import com.vikas.productservice.entity.Product;
import com.vikas.productservice.exceptions.ProductNotExistException;
import com.vikas.productservice.payload.ProductRequest;
import com.vikas.productservice.payload.ProductResponse;
import com.vikas.productservice.repositories.ProductRepository;
import com.vikas.productservice.services.ProductService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long saveProduct(ProductRequest productRequest) {
        Product product=Product.builder().productName(productRequest.getProductName()).quantity(productRequest.getQuantity())
                .price(productRequest.getPrice()).build();
        Product save = productRepository.save(product);
        return save.getProductId();

    }

    @Override
    public ProductResponse getProductById(Long productId) throws ProductNotExistException {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotExistException("ProductId doesn't exist"+productId)
        );
        ProductResponse productResponse=ProductResponse.builder().productName(product.getProductName()).quantity(product.getQuantity())
                .price(product.getPrice()).build();
        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId, int quantity) throws ProductNotExistException {
        log.info("Reduce Quantity initiated");
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotExistException("product not exist")
        );
        if(product instanceof Product){
            log.info("Checking product availability");
            if(product.getQuantity()<quantity){
                throw  new ProductNotExistException("Product out of stock");
            }
            product.setQuantity(product.getQuantity()-quantity);
            productRepository.save(product);
            log.info("Updating inventory in DB");
        }
    }
}
