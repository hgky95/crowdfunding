import { Sidebar } from "@/components/Sidebar";
import "./globals.css";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>
        <Sidebar />
        <div className="ml-64">
          {" "}
          {/* Adjust margin based on sidebar width */}
          {children}
        </div>
      </body>
    </html>
  );
}
