// SPDX-License-Identifier: MIT
pragma solidity ^0.8.27;

import "@openzeppelin/contracts/access/AccessControl.sol";

contract RoleManager is AccessControl {
    bytes32 public constant STUDENT_ROLE = keccak256("STUDENT_ROLE");
    bytes32 public constant COMMITTEE_ROLE = keccak256("COMMITTEE_ROLE");

    constructor(address initialAdmin) {
        _grantRole(DEFAULT_ADMIN_ROLE, initialAdmin);
    }

    function addStudent(
        address student
    ) public virtual onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(STUDENT_ROLE, student);
    }

    function addCommittee(
        address member
    ) public virtual onlyRole(DEFAULT_ADMIN_ROLE) {
        _grantRole(COMMITTEE_ROLE, member);
    }

    function revokeStudent(
        address student
    ) public virtual onlyRole(DEFAULT_ADMIN_ROLE) {
        _revokeRole(STUDENT_ROLE, student);
    }

    function revokeCommittee(
        address member
    ) public virtual onlyRole(DEFAULT_ADMIN_ROLE) {
        _revokeRole(COMMITTEE_ROLE, member);
    }
}
