package com.crowdfunding.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crowdfunding.enums.UserStatus;
import com.crowdfunding.exception.InvalidUserStatusException;
import com.crowdfunding.exception.UserNotFoundException;
import com.crowdfunding.model.User;
import com.crowdfunding.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public void setUserStatus(Long userId, UserStatus status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Invalid user id"));

        if (!user.getStatus().equals(UserStatus.PENDING)) {
            throw new InvalidUserStatusException("User is already verified or rejected.");
        }
        user.setStatus(status);
        userRepository.save(user);
    }
}
