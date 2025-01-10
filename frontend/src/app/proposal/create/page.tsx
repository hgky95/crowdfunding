"use client";

import { useState } from "react";
import { ethers } from "ethers";
import { ProposalForm } from "@/components/ProposalForm";

const NEXT_PUBLIC_API_URL = process.env.NEXT_PUBLIC_API_URL;

export default function CreateProposal() {
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleSubmit = async (formData: any) => {
    if (!window.ethereum) {
      alert("Please install MetaMask!");
      return;
    }

    setIsSubmitting(true);
    try {
      //   const provider = new ethers.BrowserProvider(window.ethereum);
      const provider = new ethers.JsonRpcProvider(
        process.env.NEXT_PUBLIC_RPC_URL || "http://localhost:8545"
      );
      const signer = await provider.getSigner();

      // 1. Get transaction data from backend
      const response = await fetch(
        `${NEXT_PUBLIC_API_URL}/api/proposals/transactions`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
          body: JSON.stringify(formData),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to prepare transaction");
      }

      const encodedTransaction = await response.json();

      // 2. Sign transaction
      const signedTransaction = await signer.signTransaction(
        encodedTransaction
      );

      // 3. Submit signed transaction
      const submitResponse = await fetch(
        `${NEXT_PUBLIC_API_URL}/api/proposals`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
          body: JSON.stringify({
            signedTransaction,
            ...formData,
          }),
        }
      );

      if (!submitResponse.ok) {
        throw new Error("Failed to submit proposal");
      }

      const result = await submitResponse.json();
      console.log("Proposal submitted:", result);

      // TODO: Show success message and redirect
    } catch (error) {
      console.error("Error:", error);
      // TODO: Show error message
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen bg-background">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <h1 className="text-3xl font-bold mb-8">Create New Proposal</h1>
        <ProposalForm onSubmit={handleSubmit} />
      </div>
    </div>
  );
}
