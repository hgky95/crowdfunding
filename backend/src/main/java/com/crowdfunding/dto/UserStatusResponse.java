package com.crowdfunding.dto;

import com.crowdfunding.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserStatusResponse {
    private boolean success;
    private String message;
    private UserStatus userStatus;
}
