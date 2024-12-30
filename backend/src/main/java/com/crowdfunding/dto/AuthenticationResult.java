package com.crowdfunding.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResult {
    private String token;
    private String role;
    private String address;
} 