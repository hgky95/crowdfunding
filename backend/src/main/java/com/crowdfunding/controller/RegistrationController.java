package com.crowdfunding.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final AuthenticationService authService;
    private final RoleService roleService;

    @PostMapping("/verify-wallet")
    public ResponseEntity<?> verifyWallet(
        @RequestBody WalletVerificationRequest request
    ) {
        // Verify wallet ownership via signature
        return authService.authenticate(request.getAddress(), request.getSignature());
    }

    // Admin only endpoints
    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignRole(
        @RequestBody RoleAssignmentRequest request
    ) {
        // Assign role via smart contract
        roleService.assignRole(request.getAddress(), request.getRole());
        return ResponseEntity.ok().build();
    }
} 