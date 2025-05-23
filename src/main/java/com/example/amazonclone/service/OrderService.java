package com.example.amazonclone.service;

import com.example.amazonclone.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(String userId, OrderDTO orderDTO);
    List<OrderDTO> getOrdersByUserId(String userId);
}
