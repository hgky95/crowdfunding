// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;
import {MilestoneManager} from "./MilestoneManager.sol";
import {ProposalManager} from "./ProposalManager.sol";
import "@openzeppelin/contracts/access/AccessControl.sol";
import {console} from "forge-std/console.sol";

contract AdminManager is AccessControl {
    MilestoneManager public milestoneManager;
    ProposalManager public proposalManager;

    constructor(
        address initialAdmin,
        address _milestoneManager,
        address _proposalManager
    ) {
        _grantRole(DEFAULT_ADMIN_ROLE, initialAdmin);
        milestoneManager = MilestoneManager(_milestoneManager);
        proposalManager = ProposalManager(_proposalManager);
    }

    function addStudent(address student) public onlyRole(DEFAULT_ADMIN_ROLE) {
        milestoneManager.addStudent(student);
        proposalManager.addStudent(student);
    }

    function addCommittee(
        address committee
    ) public onlyRole(DEFAULT_ADMIN_ROLE) {
        milestoneManager.addCommittee(committee);
        proposalManager.addCommittee(committee);
    }

    function transferAdminRole(
        address newAdmin
    ) public onlyRole(DEFAULT_ADMIN_ROLE) {
        console.log("Call transfer admin role");
        grantRole(DEFAULT_ADMIN_ROLE, newAdmin);
        revokeRole(DEFAULT_ADMIN_ROLE, msg.sender);
    }
}
