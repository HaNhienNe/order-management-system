package com.sangdd.omsbe.controller;

import com.sangdd.omsbe.entity.Order;
import com.sangdd.omsbe.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController (OrderService orderService) {
        this.service = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

}
