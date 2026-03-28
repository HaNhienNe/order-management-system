package com.sangdd.omsbe.repository;


import com.sangdd.omsbe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
