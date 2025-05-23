package com.example.amazonclone.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String id;

    @NotEmpty
    private List<String> productIds;

    @NotNull
    private double totalPrice;

    private String status; // PENDING, COMPLETED, etc.
}
