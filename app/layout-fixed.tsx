import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
// Temporarily use backup CSS that doesn't require Tailwind processing
import './globals-backup.css'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Product Management System',
  description: 'A modern product management system with reviews and ratings',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="min-h-screen bg-gray-50">
          {children}
        </div>
      </body>
    </html>
  )
}
