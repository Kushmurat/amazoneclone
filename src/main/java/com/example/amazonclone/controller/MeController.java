package com.example.amazonclone.controller;

import com.example.amazonclone.service.UserService;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class MeController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) throws Exception {
        FirebaseToken token = (FirebaseToken) request.getAttribute("firebaseUser");

        if (token == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        userService.initUserIfNotExists(token);

        String role = userService.getUserRole(token.getUid());

        return ResponseEntity.ok(Map.of(
                "uid", token.getUid(),
                "email", token.getEmail(),
                "role", role
        ));
    }
}
