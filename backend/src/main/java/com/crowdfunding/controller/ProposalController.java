package com.crowdfunding.controller;

import com.crowdfunding.dto.ProposalCreateRequest;
import com.crowdfunding.dto.ProposalCreateResponse;
import com.crowdfunding.dto.ProposalTransactionResponse;
import com.crowdfunding.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping("/transactions")
    public ResponseEntity<ProposalTransactionResponse> prepareTransaction(
        @RequestBody ProposalCreateRequest request
    ) {
        String encodedTransaction = proposalService.prepareProposalTransaction(request);
        return ResponseEntity.ok(new ProposalTransactionResponse(encodedTransaction));
    }

    @PostMapping
    public ResponseEntity<ProposalCreateResponse> submitProposal(
        @AuthenticationPrincipal String address,
        @RequestBody ProposalCreateRequest request
    ) {
        ProposalCreateResponse response = proposalService.submitProposal(
            request.getTransactionHash(),
            address,
            request
        );
        return ResponseEntity.ok(response);
    }
} 