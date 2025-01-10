"use client";

import { useState } from "react";
import { ethers } from "ethers";

interface ProposalFormProps {
  onSubmit: (data: any) => void;
}

export const ProposalForm = ({ onSubmit }: ProposalFormProps) => {
  const [formData, setFormData] = useState({
    name: "",
    title: "",
    story: "",
    plan: "",
  });
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);

    try {
      // TODO: Upload content to IPFS
      const contentCID = "QmContent..."; // Replace with actual IPFS upload
      const planCID = "QmPlan..."; // Replace with actual IPFS upload

      const data = {
        title: formData.title,
        contentCID,
        planCID,
      };

      await onSubmit(data);
    } catch (error) {
      console.error("Error submitting proposal:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="max-w-2xl mx-auto p-6 space-y-8">
      <div className="grid grid-cols-2 gap-6">
        <div>
          <label className="block mb-2 text-sm font-medium">Your Name</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            className="w-full p-3 border rounded-lg bg-transparent"
            placeholder="Enter your name"
            required
          />
        </div>

        <div>
          <label className="block mb-2 text-sm font-medium">
            Campaign Name
          </label>
          <input
            type="text"
            name="title"
            value={formData.title}
            onChange={handleChange}
            className="w-full p-3 border rounded-lg bg-transparent"
            placeholder="Enter campaign name"
            required
          />
        </div>
      </div>

      <div>
        <label className="block mb-2 text-sm font-medium">Story</label>
        <textarea
          name="story"
          value={formData.story}
          onChange={handleChange}
          className="w-full p-3 border rounded-lg bg-transparent min-h-[200px]"
          placeholder="Tell us your story..."
          required
        />
      </div>

      <div>
        <label className="block mb-2 text-sm font-medium">Detailed Plan</label>
        <textarea
          name="plan"
          value={formData.plan}
          onChange={handleChange}
          className="w-full p-3 border rounded-lg bg-transparent min-h-[200px]"
          placeholder="Describe your detailed plan..."
          required
        />
      </div>

      <div className="flex justify-end pt-6">
        <button
          type="submit"
          disabled={isLoading}
          className="px-8 py-3 text-white bg-green-600 rounded-lg hover:bg-green-700 disabled:opacity-50 transition-colors"
        >
          {isLoading ? "Submitting..." : "Start a Campaign"}
        </button>
      </div>
    </form>
  );
};
