"use client";

import { useState } from "react";
import { ConnectButton } from "@/components/ConnectButton";
import { AuthorizeButton } from "@/components/AuthorizeButton";
import { CampaignCard } from "@/components/CampaignCard";

const SAMPLE_CAMPAIGNS = [
  {
    title: "TMB | The Modular Bottle",
    description: "Introducing TMB | The Modular Bottle for sustainable living",
    imageUrl: "/campaign1.jpg",
    raised: "0.0 ETH",
    daysLeft: 92,
    creator: "0xad31A4238aE747DD85b736F8E",
  },
  // Add more sample campaigns...
];

export default function Home() {
  const [isConnected, setIsConnected] = useState(false);
  const [isAuthorized, setIsAuthorized] = useState(false);

  const handleConnect = (address: string) => {
    setIsConnected(true);
  };

  const handleAuthorize = (token: string, role: string) => {
    setIsAuthorized(true);
    // Store token and role in local storage or state management
    localStorage.setItem("token", token);
    localStorage.setItem("role", role);
  };

  return (
    <div className="min-h-screen">
      <header className="fixed top-0 w-full bg-background/80 backdrop-blur-sm border-b border-black/[.08] dark:border-white/[.145] z-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
          <div className="text-2xl font-bold">Crowdfunding</div>
          <div className="flex gap-4">
            <ConnectButton onConnect={handleConnect} />
            {isConnected && !isAuthorized && (
              <AuthorizeButton onAuthorize={handleAuthorize} />
            )}
          </div>
        </div>
      </header>

      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-24 pb-12">
        <h1 className="text-4xl font-bold mb-8">Featured Campaigns</h1>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {SAMPLE_CAMPAIGNS.map((campaign, index) => (
            <CampaignCard key={index} {...campaign} />
          ))}
        </div>
      </main>
    </div>
  );
}
