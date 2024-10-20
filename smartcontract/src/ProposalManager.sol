// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import {RoleManager} from "./RoleManager.sol";
import {MilestoneManager} from "./MilestoneManager.sol";
import {console} from "forge-std/console.sol";

// Contract for managing student proposals
contract ProposalManager is RoleManager {
    MilestoneManager public milestoneManager;
    enum ProposalStatus {
        Pending,
        Approved,
        Rejected
    }

    struct ProposalDetails {
        uint id;
        string title;
        string contentCID;
        string planCID;
        address student;
        ProposalStatus status;
    }

    mapping(uint => ProposalDetails) public proposals;
    uint public proposalCount;

    event ProposalSubmitted(uint indexed id, address indexed student);
    event ProposalStatusChanged(
        uint indexed id,
        address indexed student,
        ProposalStatus newStatus
    );

    constructor(
        address initialAdmin,
        address _milestoneManager
    ) RoleManager(initialAdmin) {
        milestoneManager = MilestoneManager(_milestoneManager);
    }

    function submitProposal(
        string memory _title,
        string memory _contentCID,
        string memory _planCID
    ) public onlyRole(STUDENT_ROLE) {
        proposals[proposalCount] = ProposalDetails(
            proposalCount,
            _title,
            _contentCID,
            _planCID,
            msg.sender,
            ProposalStatus.Pending
        );
        emit ProposalSubmitted(proposalCount, msg.sender);
        proposalCount++;
    }

    function changeProposalStatus(
        uint _id,
        ProposalStatus _newStatus
    ) public onlyRole(COMMITTEE_ROLE) {
        require(
            proposals[_id].status == ProposalStatus.Pending,
            "Can only change status of pending proposals"
        );
        proposals[_id].status = _newStatus;
        emit ProposalStatusChanged(_id, proposals[_id].student, _newStatus);
    }

    function getProposalsByStudent(
        address _student
    ) public view returns (ProposalDetails[] memory) {
        uint count = 0;
        for (uint i = 0; i < proposalCount; i++) {
            if (proposals[i].student == _student) {
                count++;
            }
        }

        ProposalDetails[] memory studentProposals = new ProposalDetails[](
            count
        );
        uint index = 0;
        for (uint i = 0; i < proposalCount; i++) {
            if (proposals[i].student == _student) {
                studentProposals[index] = proposals[i];
                index++;
            }
        }
        return studentProposals;
    }

    function getAllProposals() public view returns (ProposalDetails[] memory) {
        ProposalDetails[] memory allProposals = new ProposalDetails[](
            proposalCount
        );
        for (uint i = 0; i < proposalCount; i++) {
            allProposals[i] = proposals[i];
        }
        return allProposals;
    }

    function getProposal(
        uint _proposalId
    ) public view returns (ProposalDetails memory) {
        return proposals[_proposalId];
    }

    function createMilestonesForProposal(
        uint _proposalId,
        string[] memory _descriptions,
        uint[] memory _fundingAmounts,
        uint[] memory _deadlines
    ) public onlyRole(COMMITTEE_ROLE) {
        require(
            proposals[_proposalId].status == ProposalStatus.Approved,
            "Proposal not approved"
        );
        require(
            _descriptions.length == _fundingAmounts.length &&
                _fundingAmounts.length == _deadlines.length,
            "Array lengths mismatch"
        );

        for (uint i = 0; i < _descriptions.length; i++) {
            milestoneManager.createMilestonePlan(
                _proposalId,
                _descriptions[i],
                _fundingAmounts[i],
                _deadlines[i],
                msg.sender
            );
        }
    }
}
