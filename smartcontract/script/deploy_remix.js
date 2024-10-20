import { deploy } from "./ethers-lib";

(async () => {
  try {
    // 1. Deploy MilestoneManager with admin address
    const adminAddress = "0x5B38Da6a701c568545dCfcB03FcB875f56beddC4";
    const milestoneManager = await deploy("MilestoneManager", [adminAddress]);
    console.log(`MilestoneManager deployed at: ${milestoneManager.address}`);

    // 2. Deploy ProposalManager with admin address and milestoneMaker's address
    const proposalManager = await deploy("ProposalManager", [
      adminAddress,
      milestoneManager.address,
    ]);
    console.log(`ProposalManager deployed at: ${proposalManager.address}`);

    // 3. Deploy AdminManager with admin address, milestoneManager's address, and proposalManager's address
    const adminManager = await deploy("AdminManager", [
      adminAddress,
      milestoneManager.address,
      proposalManager.address,
    ]);
    console.log(`AdminManager deployed at: ${adminManager.address}`);

    // 4. Deploy MockUSDC with admin address
    const mockUSDC = await deploy("MockUSDC", [adminAddress]);
    console.log(`MockUSDC deployed at: ${mockUSDC.address}`);

    // 5. Deploy FundManager with milestoneManager's address, proposalManager's address, and mockUSDC's address
    const fundManager = await deploy("FundManager", [
      milestoneManager.address,
      proposalManager.address,
      mockUSDC.address,
    ]);
    console.log(`FundManager deployed at: ${fundManager.address}`);
  } catch (e) {
    console.log(e.message);
  }
})();
