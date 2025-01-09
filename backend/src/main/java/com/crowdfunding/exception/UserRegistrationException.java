package com.crowdfunding.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class UserRegistrationException extends RuntimeException {
    private final HttpStatus status;

    public UserRegistrationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
