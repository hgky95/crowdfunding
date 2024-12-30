package com.crowdfunding.service;

import com.crowdfunding.dto.AuthenticationResult;
import com.crowdfunding.dto.WalletVerificationRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final MessageSigningService messageSigningService;
    private final RoleService roleService;
    private final JwtService jwtService;

    public AuthenticationService(
        MessageSigningService messageSigningService,
        RoleService roleService,
        JwtService jwtService
    ) {
        this.messageSigningService = messageSigningService;
        this.roleService = roleService;
        this.jwtService = jwtService;
    }

    public AuthenticationResult authenticate(WalletVerificationRequest request) {
        // Verify signature
        if (!messageSigningService.verifySignature(
            request.getMessage(), 
            request.getSignature(), 
            request.getAddress()
        )) {
            throw new RuntimeException("Invalid signature");
        }

        // Get user role
        RoleService.UserRole role = roleService.getUserRole(request.getAddress());
        
        // Generate JWT
        String token = jwtService.generateToken(request.getAddress(), role.toString());

        return AuthenticationResult.builder()
                .token(token)
                .role(role.toString())
                .address(request.getAddress())
                .build();
    }
} 