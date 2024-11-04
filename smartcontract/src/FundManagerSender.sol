// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import {IRouterClient} from "@chainlink/contracts-ccip/src/v0.8/ccip/interfaces/IRouterClient.sol";
import {Client} from "@chainlink/contracts-ccip/src/v0.8/ccip/libraries/Client.sol";
import {IERC20} from "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import {SafeERC20} from "@openzeppelin/contracts/token/ERC20/utils/SafeERC20.sol";
import {console} from "forge-std/console.sol";

// Deploy on AVAX Fuji
contract FundManagerSender {
    using SafeERC20 for IERC20;

    IRouterClient public router;
    IERC20 public usdc;
    address public receiver;
    uint64 public destinationChainSelector; // Sepolia chain selector
    
    event CrossChainDepositSent(
        bytes32 indexed messageId,
        uint indexed proposalId,
        address indexed sender,
        uint amount
    );

    constructor(
        address _router, // Chainlink CCIP Router address on Sender side (e.g: Fuji)
        address _usdc,
        address _receiver, // Address of receiver (FundManagerReceiver) on Sepolia
        uint64 _destinationChainSelector // Sepolia chain selector
    ) {
        require(_router != address(0), "Invalid router address");
        require(_usdc != address(0), "Invalid USDC address");
        require(_receiver != address(0), "Invalid receiver address");
        
        router = IRouterClient(_router);
        usdc = IERC20(_usdc);
        receiver = _receiver;
        destinationChainSelector = _destinationChainSelector;
    }

    function depositFundsCrossChain(
        uint _proposalId,
        uint _amount
    ) external returns (bytes32 messageId) {
        require(_amount > 0, "Amount must be greater than 0");

        console.log("Approving router to spend. usdc: ", address(router));
        console.log("Approving amount: ", _amount);
        // Approve router to spend USDC - Need to approve by user (user call approve on USDC contract)
        // usdc.approve(address(router), _amount);
        
        console.log("Start to transfer usdc from sender to this contract: ", msg.sender);
        console.log("USDC address: ", address(usdc));
        // Transfer USDC from sender to this contract
        usdc.safeTransferFrom(msg.sender, address(this), _amount);

        console.log("Prepare the message data");
        // Prepare the message data
        bytes memory data = abi.encode(_proposalId, msg.sender);

        console.log("Prepare token transfer info");
        // Prepare token transfer info
        Client.EVMTokenAmount[] memory tokenAmounts = new Client.EVMTokenAmount[](1);
        tokenAmounts[0] = Client.EVMTokenAmount({
            token: address(usdc),
            amount: _amount
        });

        console.log("Prepare CCIP message");
        // Prepare CCIP message
        Client.EVM2AnyMessage memory message = Client.EVM2AnyMessage({
            receiver: abi.encode(receiver),
            data: data,
            tokenAmounts: tokenAmounts,
            extraArgs: "",
            feeToken: address(0) // Pay fees in native token
        });

        console.log("Get the fee required");
        // Get the fee required
        uint256 fee = router.getFee(destinationChainSelector, message);
        
        console.log("Ready to send cross chain with fee: ", fee);
        // Send CCIP message with tokens
        messageId = router.ccipSend{value: fee}(
            destinationChainSelector,
            message
        );

        emit CrossChainDepositSent(
            messageId,
            _proposalId,
            msg.sender,
            _amount
        );
    }

    // Function to receive native token for CCIP fees
    receive() external payable {}
}