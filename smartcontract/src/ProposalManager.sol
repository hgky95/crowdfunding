// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import {RoleManager} from "./RoleManager.sol";

// Contract for managing student proposals
contract ProposalManager is RoleManager {
    enum ProposalStatus {
        Pending,
        Approved,
        Rejected
    }

    struct ProposalDetails {
        uint id;
        string title;
        string content;
        string plan;
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

    constructor(address initialAdmin) RoleManager(initialAdmin) {}

    function submitProposal(
        string memory _title,
        string memory _content,
        string memory _plan
    ) public onlyRole(STUDENT_ROLE) {
        proposals[proposalCount] = ProposalDetails(
            proposalCount,
            _title,
            _content,
            _plan,
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
}
