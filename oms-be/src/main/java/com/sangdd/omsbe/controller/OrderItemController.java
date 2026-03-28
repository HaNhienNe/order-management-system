package com.sangdd.omsbe.controller;

import com.sangdd.omsbe.entity.OrderItem;
import com.sangdd.omsbe.service.OrderItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService service;

    public OrderItemController(OrderItemService orderItemService) {
        this.service = orderItemService;
    }

    @GetMapping
    public List<OrderItem> orderItems() {
        return service.getAllOrderItems();
    }

    @PostMapping
    public OrderItem createOrderItem(OrderItem orderItem) {
        return service.createOrderItem(orderItem);
    }

}
