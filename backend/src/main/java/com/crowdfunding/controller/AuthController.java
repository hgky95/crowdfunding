package com.crowdfunding.controller;

import com.crowdfunding.dto.AuthenticationResult;
import com.crowdfunding.dto.WalletVerificationRequest;
import com.crowdfunding.service.AuthenticationService;
import com.crowdfunding.service.MessageSigningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authService;
    private final MessageSigningService messageSigningService;

    public AuthController(
        AuthenticationService authService,
        MessageSigningService messageSigningService
    ) {
        this.authService = authService;
        this.messageSigningService = messageSigningService;
    }

    @PostMapping("/nonce")
    public ResponseEntity<String> getNonce(@RequestParam String address) {
        return ResponseEntity.ok(messageSigningService.createNonce(address));
    }

    @PostMapping("/verify")
    public ResponseEntity<AuthenticationResult> verifyWallet(
        @RequestBody WalletVerificationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
} 