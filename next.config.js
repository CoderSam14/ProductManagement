/** @type {import('next').NextConfig} */
const nextConfig = {
  // Remove 'export' for Vercel - it handles static optimization automatically
  trailingSlash: true,
  skipTrailingSlashRedirect: true,
  images: {
    unoptimized: true,
  },
  env: {
    NEXT_PUBLIC_API_URL: process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080'
  }
}

module.exports = nextConfig
