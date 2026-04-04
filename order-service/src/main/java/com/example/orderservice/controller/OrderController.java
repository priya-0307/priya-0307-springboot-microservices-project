package com.example.orderservice.controller;

import com.example.orderservice.ProductClient;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/{id}")
    public ProductDto getOrder(@PathVariable Long id){
        return productClient.getProduct(id);
    }

    //get all orders

    @GetMapping
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){

        ProductDto product = productClient.getProduct(order.getProductId());

        double total = product.getPrice() * order.getQuantity();
        order.setTotalPrice(total);

        return order;
    }

}
