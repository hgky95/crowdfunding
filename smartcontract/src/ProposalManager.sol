// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import "@openzeppelin/contracts/access/AccessControl.sol";

// Contract for managing student proposals
contract ProposalManager is AccessControl {
    bytes32 public constant STUDENT_ROLE = keccak256("STUDENT_ROLE");
    bytes32 public constant COMMITTEE_ROLE = keccak256("COMMITTEE_ROLE");

    struct ProposalDetails {
        uint id;
        string title;
        string content; //TODO should store content's hash to optimized storage
        string plan;
        address student;
        bool isApproved;
    }

    mapping(uint => ProposalDetails) public proposals;
    uint public proposalCount;

    event ProposalSubmitted(uint indexed id, address indexed student);
    event ProposalApproved(uint indexed id, address indexed student);

    constructor(address initialAdmin) {
        _grantRole(DEFAULT_ADMIN_ROLE, initialAdmin);
    }

    function addStudent(address student) public onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(STUDENT_ROLE, student);
    }

    function addCommitteeMember(
        address member
    ) public onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(COMMITTEE_ROLE, member);
    }

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
            false
        );
        emit ProposalSubmitted(proposalCount, msg.sender);
        proposalCount++;
    }

    function approveProposal(uint _id) public onlyRole(COMMITTEE_ROLE) {
        require(!proposals[_id].isApproved, "Proposal already approved");
        proposals[_id].isApproved = true;
        emit ProposalApproved(_id, proposals[_id].student);
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
