package com.example.amazonclone.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private List<String> productIds;
    private int totalAmount;
}
