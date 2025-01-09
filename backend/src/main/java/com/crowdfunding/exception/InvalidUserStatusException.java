package com.crowdfunding.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InvalidUserStatusException extends RuntimeException {
    private HttpStatus status;

    public InvalidUserStatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public InvalidUserStatusException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
}
