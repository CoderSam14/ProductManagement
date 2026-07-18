# Frontend Deployment Guide

## Vercel Deployment (Recommended)

1. **Connect Repository**: 
   - Go to https://vercel.com
   - Import your GitHub repository

2. **Configure Build Settings**:
   - Framework Preset: Next.js
   - Build Command: `npm run build`
   - Output Directory: `out`

3. **Environment Variables**:
   - `NEXT_PUBLIC_API_URL`: Your backend URL (e.g., https://your-backend.railway.app)

4. **Deploy**: Vercel will automatically deploy on every push to main branch

## Alternative: Netlify Deployment

1. **Build Settings**:
   - Build command: `npm run build`
   - Publish directory: `out`

2. **Environment Variables**: Same as Vercel

## Local Development

1. **Install dependencies**:
   ```bash
   npm install
   ```

2. **Start development server**:
   ```bash
   npm run dev
   ```

3. **Build for production**:
   ```bash
   npm run build
   ```

## Full Stack Development

Run both frontend and backend:
```bash
npm run dev:full
```

This will start:
- Backend on http://localhost:8080
- Frontend on http://localhost:3000
