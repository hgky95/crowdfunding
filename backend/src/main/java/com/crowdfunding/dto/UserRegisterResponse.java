package com.crowdfunding.dto;

import com.crowdfunding.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRegisterResponse {
    private boolean success;
    private String message;
    private User data;
} 