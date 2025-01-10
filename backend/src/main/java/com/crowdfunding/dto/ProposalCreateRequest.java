package com.crowdfunding.dto;

import lombok.Data;

@Data
public class ProposalCreateRequest {
    private String title;
    private String contentCID;  // IPFS CID for proposal content
    private String planCID;     // IPFS CID for detailed plan
    private String signedTransaction;

    public String getSignedTransaction() {
        return signedTransaction;
    }

    public void setSignedTransaction(String signedTransaction) {
        this.signedTransaction = signedTransaction;
    }
} 