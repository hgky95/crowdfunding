package com.crowdfunding.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunding.service.UserService;
import com.crowdfunding.dto.UserRegisterRequest;
import com.crowdfunding.model.User;
import com.crowdfunding.dto.UserRegisterResponse;
import com.crowdfunding.exception.UserRegistrationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserRegisterResponse.builder()
                    .success(true)
                    .message("User registered successfully")
                    .data(registeredUser)
                    .build());
        } catch (UserRegistrationException e) {
            log.error("User registration failed: ", e);
            return ResponseEntity.status(e.getStatus())
                .body(UserRegisterResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build());
        }
    }
}
