package com.crowdfunding.service;

import com.crowdfunding.dto.ProposalCreateRequest;
import com.crowdfunding.dto.ProposalCreateResponse;
import com.crowdfunding.enums.ProposalStatus;
import com.crowdfunding.exception.CrowdfundingException;
import com.crowdfunding.generated.ProposalManager;
import com.crowdfunding.model.Proposal;
import com.crowdfunding.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProposalService {
    
    private final Web3Service web3Service;
    private final ProposalRepository proposalRepository;

    public String prepareProposalTransaction(ProposalCreateRequest request) {
        validateProposalRequest(request);
        
        try {
            ProposalManager proposalManager = web3Service.loadProposalManager();
            
            String encodedTransaction = proposalManager
                .submitProposal(
                    request.getTitle(),
                    request.getContentCID(),
                    request.getPlanCID()
                )
                .encodeFunctionCall(); // return encoded transaction, used to submit signed transaction
                
            return encodedTransaction;
        } catch (Exception e) {
            log.error("Failed to prepare proposal transaction", e);
            throw new CrowdfundingException("Failed to prepare proposal transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ProposalCreateResponse submitSignedTransaction(
        String signedTransaction,
        String address,
        ProposalCreateRequest request
    ) {
        try {
            // Submit the signed transaction
            TransactionReceipt receipt = web3Service.submitSignedTransaction(signedTransaction);
            
            // Get the proposal ID from events
            BigInteger proposalId = getProposalIdFromReceipt(receipt);
            
            // Save to database
            Proposal proposal = new Proposal();
            proposal.setId(proposalId);
            proposal.setTitle(request.getTitle());
            proposal.setContentCID(request.getContentCID());
            proposal.setPlanCID(request.getPlanCID());
            proposal.setStudent(address);
            proposal.setStatus(ProposalStatus.PENDING.getValue());
            
            proposalRepository.save(proposal);
            
            return ProposalCreateResponse.builder()
                .success(true)
                .message("Proposal submitted successfully")
                .transactionHash(receipt.getTransactionHash())
                .proposalId(proposalId.longValue())
                .build();
                
        } catch (Exception e) {
            log.error("Failed to submit proposal transaction", e);
            throw new CrowdfundingException("Failed to submit proposal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateProposalRequest(ProposalCreateRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new CrowdfundingException("Title is required", HttpStatus.BAD_REQUEST);
        }
        if (request.getContentCID() == null || request.getContentCID().trim().isEmpty()) {
            throw new CrowdfundingException("Content CID is required", HttpStatus.BAD_REQUEST);
        }
        if (request.getPlanCID() == null || request.getPlanCID().trim().isEmpty()) {
            throw new CrowdfundingException("Plan CID is required", HttpStatus.BAD_REQUEST);
        }
    }

    private BigInteger getProposalIdFromReceipt(TransactionReceipt receipt) {
        // Parse the ProposalSubmitted event to get the proposal ID
        return receipt.getLogs().stream()
            .filter(log -> log.getTopics().get(0).equals(EventEncoder.encode(ProposalManager.PROPOSALSUBMITTED_EVENT)))
            .findFirst()
            .map(log -> new BigInteger(log.getTopics().get(1).substring(2), 16))
            .orElseThrow(() -> new CrowdfundingException("Failed to get proposal ID from receipt", HttpStatus.INTERNAL_SERVER_ERROR));
    }
} 