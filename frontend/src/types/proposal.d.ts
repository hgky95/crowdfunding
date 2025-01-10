export interface ProposalFormData {
  title: string;
  contentCID: string;
  planCID: string;
}

export interface ProposalResponse {
  success: boolean;
  message: string;
  transactionHash: string;
  proposalId: number;
}
