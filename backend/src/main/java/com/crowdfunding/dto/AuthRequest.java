package com.crowdfunding.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String message;
    private String signature;
    private String address;
} 