package com.assessment.Assessment.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/api/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public endpoint accessed!");
    }

    @GetMapping("/api/private")
    public ResponseEntity<String> privateEndpoint() {
        return ResponseEntity.ok("Private endpoint accessed!");
    }


}
