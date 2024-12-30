package com.crowdfunding.controller;

import com.crowdfunding.dto.AuthRequest;
import com.crowdfunding.dto.AuthResponse;
import com.crowdfunding.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/nonce")
    public ResponseEntity<String> getNonce(@RequestParam String address) {
        return ResponseEntity.ok(authService.generateNonce(address));
    }

    @PostMapping("/verify")
    public ResponseEntity<AuthResponse> verifySignature(@RequestBody AuthRequest request) {
        try {
            String role = authService.verifySignature(
                request.getMessage(),
                request.getSignature(),
                request.getAddress()
            );
            String token = authService.generateToken(request.getAddress(), role);
            return ResponseEntity.ok(new AuthResponse(token, role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 