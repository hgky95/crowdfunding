"use client";

import { useState } from "react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import {
  IconHome,
  IconFileDescription,
  IconMenu2,
  IconX,
} from "@tabler/icons-react";

interface SidebarItem {
  name: string;
  href: string;
  icon: React.ReactNode;
}

const sidebarItems: SidebarItem[] = [
  {
    name: "Home",
    href: "/",
    icon: <IconHome size={24} />,
  },
  {
    name: "Proposal",
    href: "/proposal/create",
    icon: <IconFileDescription size={24} />,
  },
];

export const Sidebar = () => {
  const [isCollapsed, setIsCollapsed] = useState(false);
  const pathname = usePathname();

  return (
    <div
      className={`fixed left-0 top-0 h-screen bg-white dark:bg-gray-900 border-r border-gray-200 dark:border-gray-800 transition-all duration-300 ${
        isCollapsed ? "w-16" : "w-64"
      }`}
    >
      <div className="flex items-center justify-between h-16 px-4">
        {!isCollapsed && (
          <span className="text-xl font-semibold">Crowdfunding</span>
        )}
        <button
          onClick={() => setIsCollapsed(!isCollapsed)}
          className="p-2 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-800"
        >
          {isCollapsed ? <IconMenu2 size={20} /> : <IconX size={20} />}
        </button>
      </div>

      <nav className="mt-4">
        {sidebarItems.map((item) => (
          <Link
            key={item.href}
            href={item.href}
            className={`flex items-center px-4 py-3 mb-1 transition-colors ${
              pathname === item.href
                ? "bg-gray-100 dark:bg-gray-800 text-primary"
                : "hover:bg-gray-50 dark:hover:bg-gray-800"
            }`}
          >
            <span className="inline-block">{item.icon}</span>
            {!isCollapsed && <span className="ml-3">{item.name}</span>}
          </Link>
        ))}
      </nav>
    </div>
  );
};
