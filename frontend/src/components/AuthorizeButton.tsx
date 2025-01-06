"use client";

import { useState } from "react";
import { ethers } from "ethers";

interface AuthorizeButtonProps {
  onAuthorize?: (token: string, role: string) => void;
}

const NEXT_PUBLIC_API_URL = process.env.NEXT_PUBLIC_API_URL;

export const AuthorizeButton = ({ onAuthorize }: AuthorizeButtonProps) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleAuthorize = async () => {
    if (!window.ethereum) {
      alert("Please install MetaMask!");
      return;
    }

    setIsLoading(true);
    try {
      const provider = new ethers.BrowserProvider(window.ethereum);
      const signer = await provider.getSigner();
      const address = await signer.getAddress();

      // 1. Get nonce
      const nonceResponse = await fetch(
        `${NEXT_PUBLIC_API_URL}/api/auth/nonce?address=${address}`,
        {
          method: "POST",
        }
      );
      const message = await nonceResponse.text();

      // 2. Sign message
      const signature = await signer.signMessage(message);

      // 3. Verify signature
      const verifyResponse = await fetch(
        `${NEXT_PUBLIC_API_URL}/api/auth/verify`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            message,
            signature,
            address,
          }),
        }
      );

      if (!verifyResponse.ok) {
        throw new Error("Failed to verify signature");
      }

      const { token, role } = await verifyResponse.json();
      onAuthorize?.(token, role); // optional chaining operator to avoid error if onAuthorize is not defined
    } catch (error) {
      console.error("Authorization failed:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <button
      onClick={handleAuthorize}
      disabled={isLoading}
      className="rounded-full border border-foreground px-6 py-2 hover:bg-foreground hover:text-background transition-colors disabled:opacity-50"
    >
      {isLoading ? "Authorizing..." : "Authorize"}
    </button>
  );
};
