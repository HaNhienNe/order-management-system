package com.sangdd.omsbe.service;

import com.sangdd.omsbe.entity.OrderItem;
import com.sangdd.omsbe.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.repository = orderItemRepository;
    }


    public List<OrderItem> getAllOrderItems() {
        return repository.findAll();
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return repository.save(orderItem);
    }
}
