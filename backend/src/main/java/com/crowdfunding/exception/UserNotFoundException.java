package com.crowdfunding.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private HttpStatus status;

    public UserNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public UserNotFoundException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

}
