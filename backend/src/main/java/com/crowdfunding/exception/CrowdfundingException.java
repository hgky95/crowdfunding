package com.crowdfunding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CrowdfundingException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public CrowdfundingException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public CrowdfundingException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
} 