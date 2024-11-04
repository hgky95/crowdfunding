// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import {FundManager} from "./FundManager.sol";
import {CCIPReceiver} from "@chainlink/contracts-ccip/src/v0.8/ccip/applications/CCIPReceiver.sol";
import {Client} from "@chainlink/contracts-ccip/src/v0.8/ccip/libraries/Client.sol";
import {IERC20} from "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import {console} from "forge-std/console.sol";

contract FundManagerReceiver is CCIPReceiver {
    FundManager public fundManager;
    address public ccipUsdc; // USDC address on Sepolia that CCIP uses

    event CrossChainDepositReceived(
        bytes32 indexed messageId,
        uint64 indexed sourceChainSelector,
        address sender,
        uint proposalId,
        uint amount
    );

    constructor(
        address _router, // Chainlink CCIP Router address on Receiver side (e.g: Sepolia)
        address _fundManager,
        address _ccipUsdc
    ) CCIPReceiver(_router) {
        require(_fundManager != address(0), "Invalid fund manager address");
        require(_ccipUsdc != address(0), "Invalid CCIP USDC address");
        
        fundManager = FundManager(_fundManager);
        ccipUsdc = _ccipUsdc;
    }

    function _ccipReceive(
        Client.Any2EVMMessage memory message
    ) internal override {
        console.log("Received the message");
        // Decode the message data
        (uint proposalId, address sender) = abi.decode(message.data, (uint, address));
        console.log("Received the porposalId: ", proposalId);
        console.log("Received the address sender: ", sender);
        
        console.log("Get the USDC token and amount from the transferred tokens");
        // Get the USDC token and amount from the transferred tokens
        Client.EVMTokenAmount[] memory tokens = message.destTokenAmounts;
        require(tokens.length == 1, "Expected only USDC token");
        require(tokens[0].token == ccipUsdc, "Invalid token received");
        
        uint amount = tokens[0].amount;

        console.log("Approve USDC spending for FundManager");
        // Approve USDC spending for FundManager
        IERC20(ccipUsdc).approve(address(fundManager), amount);
        
        console.log("Deposit funds to the FundManager on behalf of the original sender");
        // Deposit funds to the FundManager on behalf of the original sender
        fundManager.depositFunds(proposalId, amount);

        emit CrossChainDepositReceived(
            message.messageId,
            message.sourceChainSelector,
            sender,
            proposalId,
            amount
        );
    }
}