package com.example.amazonclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String userId;
    private List<String> productIds;
    private int totalAmount;
    private Instant createdAt;
}
