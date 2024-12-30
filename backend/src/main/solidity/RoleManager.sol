// SPDX-License-Identifier: MIT
pragma solidity ^0.8.26;

import "@openzeppelin/contracts/access/AccessControl.sol";

contract RoleManager is AccessControl {
    bytes32 public constant STUDENT_ROLE = keccak256("STUDENT_ROLE");
    bytes32 public constant COMMITTEE_ROLE = keccak256("COMMITTEE_ROLE");
    bytes32 public constant DONOR_ROLE = keccak256("DONOR_ROLE");

    // Role assignment events
    event RoleAssigned(address indexed user, bytes32 indexed role);
    event RoleRevoked(address indexed user, bytes32 indexed role);

    constructor(address initialAdmin) {
        _grantRole(DEFAULT_ADMIN_ROLE, initialAdmin);
    }

    function addStudent(address student) public onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(STUDENT_ROLE, student);
        emit RoleAssigned(student, STUDENT_ROLE);
    }

    function addCommittee(address member) public onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(COMMITTEE_ROLE, member);
        emit RoleAssigned(member, COMMITTEE_ROLE);
    }

    function addDonor(address donor) public onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(DONOR_ROLE, donor);
        emit RoleAssigned(donor, DONOR_ROLE);
    }

    // Role checking functions
    function isStudent(address user) public view returns (bool) {
        return hasRole(STUDENT_ROLE, user);
    }

    function isCommittee(address user) public view returns (bool) {
        return hasRole(COMMITTEE_ROLE, user);
    }

    function isDonor(address user) public view returns (bool) {
        return hasRole(DONOR_ROLE, user);
    }
}
