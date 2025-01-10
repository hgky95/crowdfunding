package com.crowdfunding.enums;

public enum ProposalStatus {
    PENDING(0),
    APPROVED(1),
    REJECTED(2);

    private final int value;

    ProposalStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

