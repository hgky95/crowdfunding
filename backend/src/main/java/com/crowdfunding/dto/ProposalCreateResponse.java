package com.crowdfunding.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposalCreateResponse {
    private boolean success;
    private String message;
    private String transactionHash;
    private Long proposalId;
} 