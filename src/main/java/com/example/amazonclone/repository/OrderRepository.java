package com.example.amazonclone.repository;

import com.example.amazonclone.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findByUserId(String userId);
    List<Order> findAll();
}
