// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import "forge-std/Test.sol";
import "../src/AdminManager.sol";
import "../src/MilestoneManager.sol";
import "../src/ProposalManager.sol";
import "../src/RoleManager.sol";
import "@openzeppelin/contracts/access/AccessControl.sol";

error AccessControlUnauthorizedAccount(bytes32 available, address required);

contract AdminManagerTest is Test {
    AdminManager public adminManager;
    MilestoneManager public milestoneManager;
    ProposalManager public proposalManager;

    address public admin = address(0x123);
    address public committee = address(0x456);
    address public student = address(0x789);

    uint256 public constant PROPOSAL_ID = 1;

    function setUp() public {
        milestoneManager = new MilestoneManager(admin);
        proposalManager = new ProposalManager(admin, address(milestoneManager));

        // Deploy AdminManager with the Milestone and Proposal managers
        adminManager = new AdminManager(
            address(admin),
            address(milestoneManager),
            address(proposalManager)
        );

        vm.startPrank(admin);
        milestoneManager.grantRole(
            milestoneManager.DEFAULT_ADMIN_ROLE(),
            address(adminManager)
        );
        proposalManager.grantRole(
            proposalManager.DEFAULT_ADMIN_ROLE(),
            address(adminManager)
        );
        adminManager.addCommittee(committee);
        adminManager.addStudent(student);
        vm.stopPrank();
    }

    function testSubmitMilestoneResult() public {
        uint milestoneId = 0;

        vm.prank(committee);
        milestoneManager.createMilestonePlan(
            PROPOSAL_ID,
            "Milestone description",
            1000,
            1678900000,
            committee
        );
        vm.prank(student);
        milestoneManager.submitMilestoneResult(
            PROPOSAL_ID,
            milestoneId,
            "Milestone result"
        );
        MilestoneManager.MilestonePlan[]
            memory milestonePlans = milestoneManager
                .getMilestonePlansByProposal(PROPOSAL_ID);
        assertEq(
            uint(milestonePlans[milestoneId].status),
            uint(MilestoneManager.MilestoneStatus.Pending)
        );
    }

    function testCannotSubmitAfterDeadline() public {
        vm.prank(committee);
        milestoneManager.createMilestonePlan(
            PROPOSAL_ID,
            "Test Milestone",
            1000,
            block.timestamp + 1 hours,
            committee
        );

        vm.warp(block.timestamp + 2 hours);

        vm.prank(student);
        vm.expectRevert("Milestone deadline has passed");
        milestoneManager.submitMilestoneResult(
            PROPOSAL_ID,
            0,
            "Late submission"
        );
    }

    function testCannotSubmitApprovedMilestone() public {
        vm.prank(committee);
        milestoneManager.createMilestonePlan(
            PROPOSAL_ID,
            "Test Milestone",
            1000,
            block.timestamp + 1 days,
            committee
        );

        vm.prank(student);
        milestoneManager.submitMilestoneResult(
            PROPOSAL_ID,
            0,
            "Milestone completed"
        );

        vm.prank(committee);
        milestoneManager.approveMilestone(PROPOSAL_ID, 0);

        vm.prank(student);
        vm.expectRevert("Milestone already submitted");
        milestoneManager.submitMilestoneResult(
            PROPOSAL_ID,
            0,
            "Resubmission attempt"
        );
    }

    function testOnlyStudentCanSubmit() public {
        assertTrue(
            milestoneManager.hasRole(
                milestoneManager.COMMITTEE_ROLE(),
                committee
            )
        );
        assertTrue(
            milestoneManager.hasRole(milestoneManager.STUDENT_ROLE(), student)
        );
        vm.prank(committee);
        milestoneManager.createMilestonePlan(
            PROPOSAL_ID,
            "Test Milestone",
            1000,
            block.timestamp + 1 days,
            committee
        );

        vm.prank(committee);
        vm.expectRevert();
        milestoneManager.submitMilestoneResult(
            PROPOSAL_ID,
            0,
            "Unauthorized submission"
        );
    }
}
