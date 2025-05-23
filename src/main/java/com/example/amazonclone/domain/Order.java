package com.example.amazonclone.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Order {
    private String id;
    private String userId;
    private List<String> productIds;
    private double totalPrice;
    private String status; // например: PENDING, COMPLETED, CANCELLED
}
