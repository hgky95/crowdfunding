// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import {MilestoneManager} from "./MilestoneManager.sol";
import {ProposalManager} from "./ProposalManager.sol";
import "@openzeppelin/contracts/access/AccessControl.sol";
import "@openzeppelin/contracts/utils/ReentrancyGuard.sol";
import "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import "@openzeppelin/contracts/token/ERC20/utils/SafeERC20.sol";

contract FundManager is AccessControl, ReentrancyGuard {
    using SafeERC20 for IERC20;

    MilestoneManager public milestoneManager;
    ProposalManager public proposalManager;
    IERC20 public usdc;

    mapping(uint => uint) public proposalFunds;
    // Mapping from proposalId to donor address to amount donated
    mapping(uint => mapping(address => uint)) public donations;
    // Mapping from proposalId to milestoneId to whether funds were disbursed
    mapping(uint => mapping(uint => bool)) public milestoneDisbursed;
    mapping(uint => bool) public projectStopped;

    bytes32 public constant COMMITTEE_ROLE = keccak256("COMMITTEE_ROLE");

    event FundsDeposited(
        uint indexed proposalId,
        address indexed donor,
        uint amount
    );
    event FundsDisbursed(
        uint indexed proposalId,
        uint indexed milestoneId,
        address indexed student,
        uint amount
    );

    constructor(
        address _milestoneManager,
        address _proposalManager,
        address _usdc
    ) {
        require(
            _milestoneManager != address(0),
            "Invalid milestone manager address"
        );
        require(
            _proposalManager != address(0),
            "Invalid proposal manager address"
        );
        require(_usdc != address(0), "Invalid USDC address");

        milestoneManager = MilestoneManager(_milestoneManager);
        proposalManager = ProposalManager(_proposalManager);
        usdc = IERC20(_usdc);
    }

    /**
     * Allows donors to deposit USDC for a specific proposal
     * @param _proposalId The ID of the proposal to fund
     * @param _amount The amount of USDC to deposit
     */
    function depositFunds(
        uint _proposalId,
        uint _amount
    ) external nonReentrant {
        require(_amount > 0, "Must deposit some USDC");
        require(!projectStopped[_proposalId], "Project is stopped");

        ProposalManager.ProposalDetails memory proposal = proposalManager
            .getProposal(_proposalId);
        require(
            proposal.status == ProposalManager.ProposalStatus.Approved,
            "Proposal must be approved"
        );

        require(
            usdc.allowance(msg.sender, address(this)) >= _amount,
            "Insufficient USDC allowance"
        );

        // Transfer USDC from donor to contract using SafeERC20
        usdc.safeTransferFrom(msg.sender, address(this), _amount);

        proposalFunds[_proposalId] += _amount;
        donations[_proposalId][msg.sender] += _amount;

        emit FundsDeposited(_proposalId, msg.sender, _amount);
    }

    /**
     * Disburses funds for a completed milestone
     */
    function disburseMilestoneFunds(
        uint _proposalId,
        uint _milestoneId
    ) external nonReentrant {
        require(!projectStopped[_proposalId], "Project is stopped");

        // Get milestone details
        MilestoneManager.MilestonePlan[] memory plans = milestoneManager
            .getMilestonePlansByProposal(_proposalId);
        require(_milestoneId < plans.length, "Invalid milestone ID");

        MilestoneManager.MilestonePlan memory milestone = plans[_milestoneId];
        require(
            milestone.status == MilestoneManager.MilestoneStatus.Approved,
            "Milestone must be approved"
        );
        require(
            !milestoneDisbursed[_proposalId][_milestoneId],
            "Funds already disbursed for this milestone"
        );

        ProposalManager.ProposalDetails memory proposal = proposalManager
            .getProposal(_proposalId);
        require(
            proposal.status == ProposalManager.ProposalStatus.Approved,
            "Proposal must be approved"
        );
        require(
            proposalFunds[_proposalId] >= milestone.fundingAmount,
            "Insufficient funds for milestone"
        );

        // Update state before transfer (Checks-Effects-Interactions pattern)
        milestoneDisbursed[_proposalId][_milestoneId] = true;
        proposalFunds[_proposalId] -= milestone.fundingAmount;

        // Transfer USDC to student using SafeERC20
        usdc.safeTransfer(proposal.student, milestone.fundingAmount);

        emit FundsDisbursed(
            _proposalId,
            _milestoneId,
            proposal.student,
            milestone.fundingAmount
        );
    }
    /**
     * Returns the total amount of funds received for a proposal (including disbursed funds)
     * @param _proposalId The ID of the proposal
     */
    function getTotalFundsReceived(
        uint _proposalId
    ) public view returns (uint totalFunds) {
        MilestoneManager.MilestonePlan[] memory plans = milestoneManager
            .getMilestonePlansByProposal(_proposalId);
        uint disbursedFunds = 0;

        for (uint i = 0; i < plans.length; i++) {
            if (milestoneDisbursed[_proposalId][i]) {
                disbursedFunds += plans[i].fundingAmount;
            }
        }

        return proposalFunds[_proposalId] + disbursedFunds;
    }

    /**
     * Returns the available refund amount for a donor
     * @param _proposalId The ID of the proposal
     * @param _donor The address of the donor
     */
    function getAvailableRefund(
        uint _proposalId,
        address _donor
    ) external view returns (uint) {
        if (!projectStopped[_proposalId]) return 0;

        uint donationAmount = donations[_proposalId][_donor];
        if (donationAmount == 0) return 0;

        uint totalFunds = getTotalFundsReceived(_proposalId);
        if (totalFunds == 0) return 0;

        uint remainingFunds = proposalFunds[_proposalId];
        return (donationAmount * remainingFunds) / totalFunds;
    }
}
