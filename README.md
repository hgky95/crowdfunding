# Crowdfunding Smart Contract Project

This repository contains a Solidity-based crowdfunding system for student projects, built with Foundry.

## Overview

The system is organized around proposal submission, committee review, milestone tracking, and fund disbursement using an ERC-20 USDC-like token.

Core flows:
- Students submit proposals.
- Committee members approve or reject proposals.
- Approved proposals receive milestone plans.
- Donors fund approved proposals in USDC.
- Funds are disbursed to students as milestones are approved.

## Repository Structure

```text
.
├── README.md
└── smartcontract/
    ├── src/            # Solidity contracts
    ├── test/           # Foundry tests
    ├── script/         # Deployment/scripts
    ├── foundry.toml    # Foundry config
    └── .github/workflows/test.yml
```

## Contracts

- `RoleManager.sol`: Base role management (`DEFAULT_ADMIN_ROLE`, `STUDENT_ROLE`, `COMMITTEE_ROLE`).
- `ProposalManager.sol`: Proposal lifecycle and committee decisions.
- `MilestoneManager.sol`: Milestone creation, submission, and review.
- `FundManager.sol`: Donation tracking, milestone disbursement, and refund calculation.
- `AdminManager.sol`: Admin utilities for role assignment and admin transfer.
- `MockUSDC.sol`: Mock USDC token (6 decimals) for local testing.

## Prerequisites

- [Foundry](https://book.getfoundry.sh/getting-started/installation)

## Getting Started

```bash
cd smartcontract
forge install
```

## Development Commands

From `smartcontract/`:

```bash
forge fmt --check
forge build --sizes
forge test -vvv
```

## CI

GitHub Actions runs:
- format checks (`forge fmt --check`)
- build (`forge build --sizes`)
- tests (`forge test -vvv`)

Workflow file: `smartcontract/.github/workflows/test.yml`

## License

This project is licensed under the [MIT License](./LICENSE).
