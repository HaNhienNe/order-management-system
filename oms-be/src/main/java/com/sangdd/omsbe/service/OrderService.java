package com.sangdd.omsbe.service;

import com.sangdd.omsbe.entity.Order;
import com.sangdd.omsbe.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository orderRepository) {
        this.repository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

}
