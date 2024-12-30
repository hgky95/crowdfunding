package com.crowdfunding.dto;

import lombok.Data;

@Data
public class WalletVerificationRequest {
    private String address;
    private String signature;
    private String message;
} 