package com.example.amazonclone.controller;

import com.example.amazonclone.dto.ProductDTO;
import com.example.amazonclone.service.ProductFirestoreService;
import com.example.amazonclone.service.UserService;
import com.google.firebase.auth.FirebaseToken;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFirestoreService productService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Получить все товары")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    @Operation(summary = "Добавить товар (только admin)")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO product, HttpServletRequest request) throws Exception {
        FirebaseToken token = (FirebaseToken) request.getAttribute("firebaseUser");
        if (token == null) return ResponseEntity.status(401).build();

        String role = userService.getUserRole(token.getUid());
        if (!"admin".equals(role)) {
            return ResponseEntity.status(403).body("Only admin can add products");
        }

        return ResponseEntity.ok(productService.saveProduct(product));
    }
}
