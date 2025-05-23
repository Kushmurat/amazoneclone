package com.example.amazonclone.model;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Order {
    private String id;
    private String userId;
    private List<String> productIds;
    private Double totalPrice;
    private Instant createdAt;
}
