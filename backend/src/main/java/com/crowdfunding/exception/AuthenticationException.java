package com.crowdfunding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public AuthenticationException(String message) {
        this(message, HttpStatus.UNAUTHORIZED);
    }
} 