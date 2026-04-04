package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //create a product
    @PostMapping
    public Product addproduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //get all products

    @GetMapping
    public List<Product> getallproducts(){
        return productRepository.findAll();
    }

    //get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product>  getproductbyid(@PathVariable Long id){
        Product product=productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("product not found by id:" +id) );
        return ResponseEntity.ok(product);
    }
}
