package com.crowdfunding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunding.dto.UserStatusResponse;
import com.crowdfunding.enums.UserStatus;
import com.crowdfunding.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/admin/users/{userId}/status")
    public ResponseEntity<UserStatusResponse> setUserStatus(@PathVariable Long userId, @RequestParam UserStatus status) {
        adminService.setUserStatus(userId, status);
        return ResponseEntity.ok(UserStatusResponse.builder()
            .success(true)
            .message("User status updated successfully")
            .userStatus(status)
            .build());
    }
}
