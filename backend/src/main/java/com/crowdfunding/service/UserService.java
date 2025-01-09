package com.crowdfunding.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.crowdfunding.dto.UserRegisterRequest;
import com.crowdfunding.enums.UserStatus;
import com.crowdfunding.exception.UserRegistrationException;
import com.crowdfunding.model.User;
import com.crowdfunding.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(UserRegisterRequest userRequest) throws UserRegistrationException {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserRegistrationException("Email already registered", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByAddress(userRequest.getAddress())) {
            throw new UserRegistrationException("Address already registered", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
            .address(userRequest.getAddress())
            .role(userRequest.getRole())
            .name(userRequest.getName())
            .email(userRequest.getEmail())
            .status(UserStatus.PENDING.getValue())
            .build();

        return userRepository.save(user);
    }
}
