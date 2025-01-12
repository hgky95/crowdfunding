package com.crowdfunding.service;

import com.crowdfunding.generated.AdminManager;
import com.crowdfunding.generated.FundManager;
import com.crowdfunding.generated.MilestoneManager;
import com.crowdfunding.generated.ProposalManager;
import com.crowdfunding.generated.RoleManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.http.HttpStatus;
import com.crowdfunding.exception.CrowdfundingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class Web3Service {
    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final Credentials credentials;

    @Value("${contract.admin-manager.address}")
    private String adminManagerAddress;

    @Value("${contract.proposal-manager.address}")
    private String proposalManagerAddress;

    @Value("${contract.milestone-manager.address}")
    private String milestoneManagerAddress;

    @Value("${contract.fund-manager.address}")
    private String fundManagerAddress;


    public AdminManager loadAdminManager(Credentials credentials) {
        return AdminManager.load(adminManagerAddress, web3j, credentials, gasProvider);
    }

    // public ProposalManager loadProposalManager(Credentials credentials) {
    //     return ProposalManager.load(proposalManagerAddress, web3j, credentials, gasProvider);
    // }
    public ProposalManager loadProposalManager() {
        return ProposalManager.load(proposalManagerAddress, web3j, credentials, gasProvider);
    }

    public MilestoneManager loadMilestoneManager(Credentials credentials) {
        return MilestoneManager.load(milestoneManagerAddress, web3j, credentials, gasProvider);
    }

    public FundManager loadFundManager(Credentials credentials) {
        return FundManager.load(fundManagerAddress, web3j, credentials, gasProvider);
    }

    public RoleManager loadRoleManager() {
        // RoleManager functionality is included in both MilestoneManager and ProposalManager
        // So we can use either of them to load the RoleManager
        return RoleManager.load(milestoneManagerAddress, web3j, credentials, gasProvider);
    }

    public TransactionReceipt submitSignedTransaction(String signedTransaction) {
        try {
            // First send the transaction and get the transaction hash
            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedTransaction).send();
            if(ethSendTransaction.getError() != null) {
                log.info(ethSendTransaction.getError().getMessage());
                throw new CrowdfundingException(ethSendTransaction.getError().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String transactionHash = ethSendTransaction.getTransactionHash();

            // Then wait for the transaction receipt using the hash
            return web3j.ethGetTransactionReceipt(transactionHash)
                .send()
                .getTransactionReceipt()
                .orElseThrow(() -> new CrowdfundingException(
                    "Failed to get transaction receipt", 
                    HttpStatus.INTERNAL_SERVER_ERROR));
                    
        } catch (Exception e) {
            log.error("Failed to submit transaction", e);
            throw new CrowdfundingException("Failed to submit transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public TransactionReceipt waitForTransaction(String transactionHash) {
        try {
            // Wait for transaction to be mined
            return web3j.ethGetTransactionReceipt(transactionHash)
                .send()
                .getTransactionReceipt()
                .orElseThrow(() -> new CrowdfundingException(
                    "Failed to get transaction receipt", 
                    HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (Exception e) {
            log.error("Failed to get transaction receipt", e);
            throw new CrowdfundingException("Failed to get transaction receipt", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 