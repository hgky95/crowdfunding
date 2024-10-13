// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.13;

import {Script, console} from "forge-std/Script.sol";
import {MilestoneManager} from "../src/MilestoneManager.sol";

contract CounterScript is Script {
    MilestoneManager public counter;

    function setUp() public {}

    function run() public {
        vm.startBroadcast();

        counter = new MilestoneManager(
            address(0x5B38Da6a701c568545dCfcB03FcB875f56beddC4)
        );

        vm.stopBroadcast();
    }
}
