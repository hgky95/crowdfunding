// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;
import {RoleManager} from "./RoleManager.sol";
import {console} from "forge-std/console.sol";

contract MilestoneManager is RoleManager {
    enum MilestoneStatus {
        Todo,
        Pending,
        Approved,
        Rejected
    }

    constructor(address initialAdmin) RoleManager(initialAdmin) {}

    struct MilestonePlan {
        uint id;
        uint proposalId;
        string description;
        uint fundingAmount;
        uint deadline;
        MilestoneStatus status;
    }

    struct MilestoneSubmission {
        uint milestoneId;
        string content; // the actual work of this milestone
        uint submissionDate;
    }

    mapping(uint => MilestonePlan[]) public milestonePlans;
    mapping(bytes32 => MilestoneSubmission) public milestoneSubmissions;

    event MilestonePlanCreated(
        uint indexed proposalId,
        uint indexed milestoneId,
        string description,
        uint fundingAmount
    );
    event MilestonePlanResultSubmitted(
        uint indexed proposalId,
        uint indexed milestoneId,
        address indexed student,
        string content
    );
    event MilestoneApproved(
        uint indexed proposalId,
        uint indexed milestoneId,
        address indexed updaterAddr
    );
    event MilestoneRejected(
        uint indexed proposalId,
        uint indexed milestoneId,
        address indexed updaterAddr,
        string reason
    );

    function createMilestonePlan(
        uint _proposalId,
        string memory _description,
        uint _fundingAmount,
        uint _deadline,
        address caller
    ) public {
        require(
            hasRole(COMMITTEE_ROLE, caller),
            "Caller must have committee role"
        );
        console.log("Calling createMilestonePlan");
        uint milestoneId = milestonePlans[_proposalId].length == 0
            ? 0
            : milestonePlans[_proposalId].length;
        console.log("New milestoneId: ", milestoneId);
        MilestonePlan memory newMilestone = MilestonePlan(
            milestoneId,
            _proposalId,
            _description,
            _fundingAmount,
            _deadline,
            MilestoneStatus.Todo
        );
        console.log("create MilestonePlan");

        milestonePlans[_proposalId].push(newMilestone);
        console.log("push MilestonePlan");

        emit MilestonePlanCreated(
            _proposalId,
            milestoneId,
            _description,
            _fundingAmount
        );
    }

    function submitMilestoneResult(
        uint _proposalId,
        uint _milestoneId,
        string memory _content
    ) public onlyRole(STUDENT_ROLE) {
        MilestonePlan storage plan = milestonePlans[_proposalId][_milestoneId];
        require(
            plan.status != MilestoneStatus.Approved,
            "Milestone already submitted"
        );
        require(
            block.timestamp <= plan.deadline,
            "Milestone deadline has passed"
        );
        bytes32 milestoneKey = getMilestoneKey(_proposalId, _milestoneId);
        milestoneSubmissions[milestoneKey] = MilestoneSubmission(
            _milestoneId,
            _content,
            block.timestamp
        );

        plan.status = MilestoneStatus.Pending;
        emit MilestonePlanResultSubmitted(
            _proposalId,
            _milestoneId,
            msg.sender,
            _content
        );
    }

    function approveMilestone(
        uint _proposalId,
        uint _milestoneId
    ) public onlyRole(COMMITTEE_ROLE) {
        MilestonePlan storage plan = milestonePlans[_proposalId][_milestoneId];
        require(
            plan.status != MilestoneStatus.Approved,
            "Milestone already approved"
        );

        plan.status = MilestoneStatus.Approved;
        emit MilestoneApproved(_proposalId, _milestoneId, msg.sender);
    }

    function rejectMilestone(
        uint _proposalId,
        uint _milestoneId,
        string memory rejectedReason
    ) public onlyRole(COMMITTEE_ROLE) {
        MilestonePlan storage plan = milestonePlans[_proposalId][_milestoneId];
        require(
            plan.status != MilestoneStatus.Approved,
            "Milestone already approved"
        );

        plan.status = MilestoneStatus.Rejected;
        emit MilestoneRejected(
            _proposalId,
            _milestoneId,
            msg.sender,
            rejectedReason
        );
    }

    function getMilestonePlansByProposal(
        uint _proposalId
    ) public view returns (MilestonePlan[] memory) {
        return milestonePlans[_proposalId];
    }

    function getMilestoneSubmission(
        uint _proposalId,
        uint _milestoneId
    ) public view returns (MilestoneSubmission memory) {
        bytes32 milestoneKey = getMilestoneKey(_proposalId, _milestoneId);
        return milestoneSubmissions[milestoneKey];
    }

    // Helper function to generate a unique key for each milestone
    function getMilestoneKey(
        uint _proposalId,
        uint _milestoneId
    ) internal pure returns (bytes32) {
        return keccak256(abi.encodePacked(_proposalId, _milestoneId));
    }
}
