package com.vikas.productservice.controllers;

import com.vikas.productservice.entity.Product;
import com.vikas.productservice.exceptions.ProductNotExistException;
import com.vikas.productservice.payload.ProductRequest;
import com.vikas.productservice.payload.ProductResponse;
import com.vikas.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vikas/product")
public class ProductController {
         private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //adding product to db
    @PostMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        Long productId = productService.saveProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long productId) throws ProductNotExistException {
        ProductResponse productById = productService.getProductById(productId);
        return  new ResponseEntity<>(productById,HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId,@RequestParam int quantity) throws ProductNotExistException {
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
