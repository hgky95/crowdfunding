package com.crowdfunding.dto;

import lombok.Data;

@Data
public class ProposalCreateRequest {
    private String title;
    private String contentCID;  // IPFS CID for proposal content
    private String planCID;     // IPFS CID for detailed plan
    private String transactionHash;

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }
} 