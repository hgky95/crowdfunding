package com.crowdfunding.enums;

public enum UserStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}

