// SPDX-License-Identifier: MIT
pragma solidity ^0.8.26;

import {Script} from "forge-std/Script.sol";
import {MockUSDC} from "../src/MockUSDC.sol";
import {RoleManager} from "../src/RoleManager.sol";
import {MilestoneManager} from "../src/MilestoneManager.sol";
import {ProposalManager} from "../src/ProposalManager.sol";
import {FundManager} from "../src/FundManager.sol";
import {AdminManager} from "../src/AdminManager.sol";
import {console} from "forge-std/console.sol";

contract DeploymentScript is Script {
    function run() external {
        // Get deployment private key from environment
        uint256 deployerPrivateKey = vm.envUint("PRIVATE_KEY");
        address deployer = vm.addr(deployerPrivateKey);
        console.log("Deployer address:", deployer);

        // Start broadcasting transactions
        vm.startBroadcast(deployerPrivateKey);

        // 1. Deploy MockUSDC
        MockUSDC usdc = new MockUSDC(deployer);
        console.log("MockUSDC deployed at:", address(usdc));

        // 2. Deploy MilestoneManager
        MilestoneManager milestoneManager = new MilestoneManager(deployer);
        console.log("MilestoneManager deployed at:", address(milestoneManager));

        // 3. Deploy ProposalManager with MilestoneManager address
        ProposalManager proposalManager = new ProposalManager(
            deployer,
            address(milestoneManager)
        );
        console.log("ProposalManager deployed at:", address(proposalManager));

        // 4. Deploy FundManager with required addresses
        FundManager fundManager = new FundManager(
            address(milestoneManager),
            address(proposalManager),
            address(usdc)
        );
        console.log("FundManager deployed at:", address(fundManager));

        // 5. Deploy AdminManager with required addresses
        AdminManager adminManager = new AdminManager(
            deployer,
            address(milestoneManager),
            address(proposalManager)
        );
        console.log("AdminManager deployed at:", address(adminManager));

        // 6. Setup initial roles
        // Grant DEFAULT_ADMIN_ROLE to AdminManager in both MilestoneManager and ProposalManager
        milestoneManager.grantRole(
            milestoneManager.DEFAULT_ADMIN_ROLE(),
            address(adminManager)
        );
        proposalManager.grantRole(
            proposalManager.DEFAULT_ADMIN_ROLE(),
            address(adminManager)
        );

        // 6. Setup initial roles
        // Grant COMMITTEE_ROLE to FundManager in MilestoneManager
        // milestoneManager.addCommittee(address(fundManager));

        // // Optional: Setup test accounts if needed
        // if (block.chainid == 31337) {
        //     // Local testnet
        //     // Test accounts
        //     address testCommittee = makeAddr("committee");
        //     address testStudent = makeAddr("student");

        //     // Grant roles
        //     adminManager.addCommittee(testCommittee);
        //     adminManager.addStudent(testStudent);

        //     // Mint some test USDC
        //     usdc.mint(testCommittee, 1000000 * 10 ** 6); // 1M USDC
        //     usdc.mint(testStudent, 1000000 * 10 ** 6); // 1M USDC

        //     console.log("Test committee account:", testCommittee);
        //     console.log("Test student account:", testStudent);
        // }

        vm.stopBroadcast();

        // Log deployment addresses for verification
        console.log("\nDeployment Summary:");
        console.log("-------------------");
        console.log("USDC:", address(usdc));
        console.log("MilestoneManager:", address(milestoneManager));
        console.log("ProposalManager:", address(proposalManager));
        console.log("FundManager:", address(fundManager));
        console.log("AdminManager:", address(adminManager));
    }

    // Helper function to create deterministic addresses for testing
    // function makeAddr(string memory name) public returns (address) {
    //     return address(uint160(uint256(keccak256(abi.encodePacked(name)))));
    // }
}
