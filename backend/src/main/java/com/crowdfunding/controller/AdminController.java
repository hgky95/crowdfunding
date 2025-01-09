package com.crowdfunding.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunding.dto.AdminSetUserStatusRequest;
import com.crowdfunding.dto.UserStatusResponse;
import com.crowdfunding.enums.UserStatus;
import com.crowdfunding.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/users/status")
    public ResponseEntity<UserStatusResponse> setUserStatus(@RequestBody AdminSetUserStatusRequest request) {
        if (request.getStatus() != UserStatus.APPROVED && request.getStatus() != UserStatus.REJECTED) {
            throw new IllegalArgumentException("Status must be either APPROVED or REJECTED");
        }
        adminService.setUserStatus(request.getUserId(), request.getStatus());
        return ResponseEntity.ok(UserStatusResponse.builder()
            .success(true)
            .message("User status updated successfully")
            .userStatus(request.getStatus())
            .build());
    }
}
