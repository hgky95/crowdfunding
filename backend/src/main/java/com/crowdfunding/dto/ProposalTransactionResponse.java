package com.crowdfunding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProposalTransactionResponse {
    private String encodedTransaction;
} 