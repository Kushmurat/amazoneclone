package com.example.amazonclone.controller;

import com.example.amazonclone.dto.OrderDTO;
import com.example.amazonclone.service.OrderService;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDTO orderDTO, HttpServletRequest request) {
        FirebaseToken token = (FirebaseToken) request.getAttribute("firebaseUser");
        if (token == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String userId = token.getUid();
        return ResponseEntity.ok(orderService.createOrder(userId, orderDTO));
    }

    @GetMapping
    public ResponseEntity<?> getOrders(HttpServletRequest request) {
        FirebaseToken token = (FirebaseToken) request.getAttribute("firebaseUser");
        if (token == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String userId = token.getUid();
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
}
