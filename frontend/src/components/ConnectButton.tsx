"use client";

import { useState, useCallback } from "react";
import { ethers } from "ethers";

interface ConnectButtonProps {
  onConnect?: (address: string) => void;
}

export const ConnectButton = ({ onConnect }: ConnectButtonProps) => {
  const [address, setAddress] = useState<string>("");
  const [isConnected, setIsConnected] = useState(false);

  const handleConnect = useCallback(async () => {
    try {
      if (!window.ethereum) {
        alert("Please install MetaMask!");
        return;
      }

      const provider = new ethers.BrowserProvider(window.ethereum);
      const accounts = await provider.send("eth_requestAccounts", []);
      const currentAddress = accounts[0];

      setAddress(currentAddress);
      setIsConnected(true);

      if (onConnect) {
        onConnect(currentAddress);
      }
    } catch (error) {
      console.error("Failed to connect:", error);
    }
  }, [onConnect]);

  return (
    <button
      onClick={handleConnect}
      className="rounded-full bg-foreground text-background px-6 py-2 hover:bg-[#383838] dark:hover:bg-[#ccc] transition-colors"
    >
      {isConnected
        ? `${address.slice(0, 6)}...${address.slice(-4)}`
        : "Connect"}
    </button>
  );
};
