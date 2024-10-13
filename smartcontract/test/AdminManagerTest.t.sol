// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import "forge-std/Test.sol";
import "../src/AdminManager.sol";
import "../src/MilestoneManager.sol";
import "../src/ProposalManager.sol";
import "../src/RoleManager.sol";
import "@openzeppelin/contracts/access/AccessControl.sol";

contract AdminManagerTest is Test {
    AdminManager public adminManager;
    MilestoneManager public milestoneManager;
    ProposalManager public proposalManager;

    address public admin = address(0x123);
    address public committee = address(0x456);
    address public student = address(0x789);

    function setUp() public {
        milestoneManager = new MilestoneManager(admin);
        proposalManager = new ProposalManager(admin, address(milestoneManager));

        adminManager = new AdminManager(
            address(admin),
            address(milestoneManager),
            address(proposalManager)
        );

        // Assign roles
        vm.startPrank(admin);
        milestoneManager.grantRole(
            milestoneManager.DEFAULT_ADMIN_ROLE(),
            address(adminManager)
        );
        proposalManager.grantRole(
            proposalManager.DEFAULT_ADMIN_ROLE(),
            address(adminManager)
        );
        vm.stopPrank();
    }

    function testAddStudent() public {
        // Simulate the admin adding a student
        vm.prank(admin);

        // Add the student using adminManager
        adminManager.addStudent(student);
        bool isStudentOnMM = milestoneManager.hasRole(
            milestoneManager.STUDENT_ROLE(),
            student
        );
        bool isStudentOnPM = proposalManager.hasRole(
            milestoneManager.STUDENT_ROLE(),
            student
        );
        assertTrue(isStudentOnMM);
        assertTrue(isStudentOnPM);
    }

    function testAddCommittee() public {
        vm.prank(admin);

        adminManager.addCommittee(student);
        bool isCommitteeOnMM = milestoneManager.hasRole(
            milestoneManager.COMMITTEE_ROLE(),
            student
        );
        bool isCommitteeOnPM = proposalManager.hasRole(
            milestoneManager.COMMITTEE_ROLE(),
            student
        );
        assertTrue(isCommitteeOnMM);
        assertTrue(isCommitteeOnPM);
    }

    function testCreateMilestonePlan() public {
        uint proposalId = 1;
        string memory description = "Milestone description";
        uint fundingAmount = 1000;
        uint deadline = block.timestamp + 1 days;

        vm.prank(admin);
        adminManager.addCommittee(committee);
        vm.stopPrank();

        // Simulate committee member creating a milestone plan
        vm.prank(committee);
        milestoneManager.createMilestonePlan(
            proposalId,
            description,
            fundingAmount,
            deadline,
            committee
        );

        MilestoneManager.MilestonePlan[] memory plans = milestoneManager
            .getMilestonePlansByProposal(proposalId);
        assertEq(plans.length, 1);
        assertEq(plans[0].description, description);
        assertEq(plans[0].fundingAmount, fundingAmount);
    }

    function testTransferAdminRole() public {
        address newAdmin = address(0xABC);

        vm.prank(admin);
        adminManager.transferAdminRole(newAdmin);

        assertTrue(
            adminManager.hasRole(adminManager.DEFAULT_ADMIN_ROLE(), newAdmin)
        );
        assertFalse(
            adminManager.hasRole(adminManager.DEFAULT_ADMIN_ROLE(), admin)
        );
    }
}
