package com.sangdd.omsbe.repository;


import com.sangdd.omsbe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
