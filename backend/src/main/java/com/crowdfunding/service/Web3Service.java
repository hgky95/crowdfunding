package com.crowdfunding.service;

import com.crowdfunding.generated.AdminManager;
import com.crowdfunding.generated.FundManager;
import com.crowdfunding.generated.MilestoneManager;
import com.crowdfunding.generated.ProposalManager;
import com.crowdfunding.generated.RoleManager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

@Service
@RequiredArgsConstructor
public class Web3Service {
    private final Web3j web3j;
    private final ContractGasProvider gasProvider;

    @Value("${contract.admin-manager.address}")
    private String adminManagerAddress;

    @Value("${contract.proposal-manager.address}")
    private String proposalManagerAddress;

    @Value("${contract.milestone-manager.address}")
    private String milestoneManagerAddress;

    @Value("${contract.fund-manager.address}")
    private String fundManagerAddress;

    @Value("${contract.role-manager.address}")
    private String roleManagerAddress;

    public AdminManager loadAdminManager(Credentials credentials) {
        return AdminManager.load(adminManagerAddress, web3j, credentials, gasProvider);
    }

    public ProposalManager loadProposalManager(Credentials credentials) {
        return ProposalManager.load(proposalManagerAddress, web3j, credentials, gasProvider);
    }

    public MilestoneManager loadMilestoneManager(Credentials credentials) {
        return MilestoneManager.load(milestoneManagerAddress, web3j, credentials, gasProvider);
    }

    public FundManager loadFundManager(Credentials credentials) {
        return FundManager.load(fundManagerAddress, web3j, credentials, gasProvider);
    }

    public RoleManager loadRoleManager(Credentials credentials) {
        return RoleManager.load(roleManagerAddress, web3j, credentials, gasProvider);
    }
} 