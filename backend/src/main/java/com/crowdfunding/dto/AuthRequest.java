package com.crowdfunding.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String address;
    private String message;
    private String signature;
} 