package com.crowdfunding.dto;

import com.crowdfunding.enums.UserStatus;

import lombok.Data;

@Data
public class AdminSetUserStatusRequest {
    private Long userId;
    private UserStatus status;
}
